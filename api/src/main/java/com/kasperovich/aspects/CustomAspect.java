package com.kasperovich.aspects;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class CustomAspect {

    @Pointcut("execution(* com.kasperovich.service.*.*.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println(joinPoint.getArgs().length);

        log.info("Method " + joinPoint.getSignature().getName() + " start");

        Object proceed = joinPoint.proceed();

        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        return proceed;
    }
}

