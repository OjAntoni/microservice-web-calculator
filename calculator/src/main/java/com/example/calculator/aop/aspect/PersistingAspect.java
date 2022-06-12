package com.example.calculator.aop.aspect;

import com.example.commonlogic.domain.operation.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Aspect
@Configuration
public class PersistingAspect {
    private final JmsTemplate jmsTemplate;

    public PersistingAspect(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Pointcut("@annotation(com.example.calculator.aop.annotation.Persisting)")
    public void methodsTaggedWithPersisting(){
        //pointcut for methods which are annotated with @Persisting
    }

    @Pointcut("execution(* *(com.example.commonlogic.domain.operation.Operation,..))")
    public void methodsUsingOperationsAsArg(){
        //pointcut for methods which use Operation entity as one of the arguments
    }

    @Around(value = "methodsTaggedWithPersisting() && methodsUsingOperationsAsArg()")
    public Operation save(ProceedingJoinPoint jp) throws Throwable {
        Operation op1 = (Operation) jp.proceed();
        if(op1!=null){
            jmsTemplate.convertAndSend("operation-queue", op1);
        }
        return op1;
    }
}
