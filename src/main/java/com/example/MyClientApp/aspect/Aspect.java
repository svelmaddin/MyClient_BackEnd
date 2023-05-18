package com.example.MyClientApp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {


    @Before("execution(* com.example.MyClientApp.service.*.*(..))")
    void beforeService(JoinPoint joinPoint) {
        log.info(joinPoint + "args: {}", joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.MyClientApp.service.*.*(..))", returning = "returnValue")
    void afterService(JoinPoint joinPoint, Object returnValue) {
        log.info(joinPoint + "return: {}", returnValue);
    }

    @Before("execution(* com.example.MyClientApp.controller.*.*(..))")
    void beforeController(JoinPoint joinPoint) {
        log.info(joinPoint + "args: {}", joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.MyClientApp.controller.*.*(..))", returning = "returnValue")
    void afterController(JoinPoint joinPoint, Object returnValue) {
        log.info(joinPoint + "return: {}", returnValue);
    }

}
