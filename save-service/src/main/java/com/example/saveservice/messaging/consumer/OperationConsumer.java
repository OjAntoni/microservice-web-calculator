package com.example.saveservice.messaging.consumer;

import com.example.commonlogic.domain.operation.Operation;
import com.example.saveservice.repository.OperationRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OperationConsumer {
    private final OperationRepository operationRepository;

    public OperationConsumer(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @JmsListener(destination = "operation-queue")
    private void saveOperation(Operation operation){
        operationRepository.save(operation);
    }
}
