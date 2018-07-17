package com.clopeza.api.login.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInOutAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogInOutAspect.class);


    @Around("execution(* com.clopeza.api.login.service..*.*(..))")
    public Object logInOut(ProceedingJoinPoint joinPoint) throws Throwable {

        LOG.info("Method {} IN", joinPoint.getSignature().getName());

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            LOG.info(
                    "Parameter {}  : {}",
                    i,
                    joinPoint.getArgs()[i] != null ?
                            joinPoint.getArgs()[i].toString() :
                            joinPoint.getArgs()[i]);
        }

        Object ret = joinPoint.proceed();

        LOG.info("Method {} OUT", joinPoint.getSignature().getName());

        return ret;
    }

}
