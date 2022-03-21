package by.innowise.monolithwebcalculator.domain.operation;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "operations")
@NamedNativeQuery(name = "Operation.mostPopular", query = "select max(x.type) from (select type, count(operations.type) from operations group by operations.type) as x")
public class Operation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",
    strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private double argOne;
    private double argTwo;
    private double result;
    @Column(name = "type", nullable = false)
    private OperationType operationType;

    public Operation(double argOne, double argTwo, double result, OperationType operationType) {
        this.argOne = argOne;
        this.argTwo = argTwo;
        this.result = result;
        this.operationType = operationType;
    }
}
