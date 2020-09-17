package ru.geekbrains.shop.aspects;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {

    @After("execution(* ru.geekbrains.shop.services..*(..))")
    public void profileAllMethods(JoinPoint joinPoint) {
        log.info("Method {} has been executed successfully !", joinPoint.toShortString());
    }

}