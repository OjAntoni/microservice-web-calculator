package by.innowise.monolithwebcalculator.cache;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class OperationMatcher implements ArgumentMatcher<Operation> {

    private Operation left;

    @Override
    public boolean matches(Operation right) {
        return left.getArgOne() == right.getArgOne() &&
                left.getArgTwo() == right.getArgTwo() &&
                left.getResult() == right.getResult() &&
                left.getId().equals(right.getId());
    }
}
