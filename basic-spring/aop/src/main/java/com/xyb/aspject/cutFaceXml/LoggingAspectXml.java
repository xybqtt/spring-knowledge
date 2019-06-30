package com.xyb.aspject.cutFaceXml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * xml形式的aop
 */
public class LoggingAspectXml {

    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("xmlbefore：" + methodName + "， args：" + args);
    }

    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("xmlafter：" + methodName + "， args：" + args);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("xmlAfterThrowing：" + methodName + "， ex:" + e);
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("xmlAfterReturning：" + methodName + "， result:" + result);
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("xmlaround：" + proceedingJoinPoint.getSignature().getName());

    }
}
