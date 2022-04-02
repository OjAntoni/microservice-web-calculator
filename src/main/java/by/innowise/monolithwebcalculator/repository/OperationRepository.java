package by.innowise.monolithwebcalculator.repository;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
    @Query(name = "Operation.mostPopular")
    OperationType findMostPopular();
    Operation getOperationById(UUID uuid);
}
