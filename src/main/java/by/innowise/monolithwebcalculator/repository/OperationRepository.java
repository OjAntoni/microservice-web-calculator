package by.innowise.monolithwebcalculator.repository;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query(name = "Operation.mostPopular")
    OperationType findMostPopular();
}
