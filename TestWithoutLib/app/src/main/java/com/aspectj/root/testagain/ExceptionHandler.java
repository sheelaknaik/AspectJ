package com.aspectj.root.testagain;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Exception handling class
 */
@Aspect
public class ExceptionHandler {
    /***
     * Logs the exception details
     *
     */
    private static final String TAG ="ExceptionHandler";

    @Pointcut("execution(* *(..))")
    public void exceptionEntryPoint() {
    }

    @AfterThrowing(pointcut = "exceptionEntryPoint()", throwing = "throwable")
    public void ExceptionLogging(JoinPoint joinPoint, Throwable throwable) {

        System.out.println("Logging exception ");
    }

}
