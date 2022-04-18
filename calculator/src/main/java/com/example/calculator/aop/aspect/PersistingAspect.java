package com.example.calculator.aop.aspect;

import com.example.calculator.repository.OperationRepository;
import com.example.commonlogic.domain.operation.Operation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PersistingAspect {
    private final OperationRepository operationRepository;

    public PersistingAspect(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Pointcut("@annotation(com.example.calculator.aop.annotation.Persisting)")
    public void methodsTaggedWithPersisting(){}

    @Pointcut("execution(* *(com.example.commonlogic.domain.operation.Operation,..)) && args(op,..)")
    public void methodsUsingOperationsAsArg(Operation op){}

    @AfterReturning(value = "methodsTaggedWithPersisting() && methodsUsingOperationsAsArg(op)", argNames = "op")
    public void save(Operation op){
        if(op!=null){
            operationRepository.save(op);
        }
    }
}
