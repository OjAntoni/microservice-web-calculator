package com.example.calculator.service;

import com.example.calculator.aop.annotation.Persisting;
import com.example.commonlogic.domain.operation.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Persisting
    public void doSmth(Operation operation){
    }
}
