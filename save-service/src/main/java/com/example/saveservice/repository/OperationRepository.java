package com.example.saveservice.repository;

import com.example.commonlogic.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
}
