package com.xyb.aspject.cutFaceAnno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 日志切面。
 * 把类声明为切面：需要把类放入Ioc，且声明为切面(@Aspect)。
 * @Order是说如果有多个切面同时切了一个类的方法，那么哪个切面应该先执行，后面数字越小，优先级越高。
 */
@Order(1)
@Aspect
@Component
public class LoggingAspect {

    // @Before是前置通知：在目标方法开始之前执行。
    // execution(方法的声明的地方，可以用*代替)。比如* com.xyb.*(int, int)表示所有方法修饰类型的com.xyb包下的所有参数为(int, int)的方法
    // JointPoint：获取方法的一些信息。
    @Before("execution(public int com.xyb.aspject.Calc.div(int, int))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("before：" + methodName + "， args：" + args);
    }

    // 后置通知，无论是否发生了异常。后置通知中还不能访问目标方法执行的结果。需要在返回通知中访问。
    @After("execution(public int com.xyb.aspject.Calc.div(int, int))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("after：" + methodName + "， args：" + args);
    }

    // 返回通知
    @AfterReturning(value = "execution(public int com.xyb.aspject.Calc.div(int, int))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("afterReturning：" + methodName + "， args：" + args + "，result：" + result);
    }

    /**
     * 注意方法内部被自己捕获的异常不会走异常通知。
     * @param joinPoint
     * @param ex 也可指定异常，在出现特定异常时，在运行异常通知。
     */
    @AfterThrowing(value = "execution(public int com.xyb.aspject.Calc.div(int, int))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("AfterThrowing：" + methodName + "， exception：" + ex);

    }


    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数。环绕通知可以实现其它通知的全部功能。
     * 类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 且环绕通知必须有返回值，返回值即为目标方法的返回值。
     * @param pjd
     */
    @Around(value = "execution(public int com.xyb.aspject.Calc.add(int, int))")
    public Object aroundMethod(ProceedingJoinPoint pjd){
        System.out.println("aroundMethod");

        Object result = null;
        String methodName = pjd.getSignature().getName();

        // 执行目标方法
        try{
            // 前置通知
            System.out.println("The method " + methodName);
            result = pjd.proceed();

            // 返回通知
            System.out.println("The method " + methodName + "end.");
        } catch (Throwable e){
            // 异常通知
            System.out.println("The method ex：" + e);
        }
        // 后置通知
        System.out.println("The method " + methodName + "end.");
        return 100;

    }


    /**
     * 定义一个方法，用于声明切入点表达式，一般地，该方法中不在需要添入其它代码。
     * 主要是为了不写很多重复的切入点表达式。也可以被用在其它切面的切入点上，
     * 例如@After("类全限定名.declareJointPointExpression()")
     */
    @Pointcut(value = "execution(public int com.xyb.aspject.Calc.sub(int, int))")
    public void declareJointPointExpression(){

    }

    /**
     * 使用方法名来引用当前的切入点表达式，避免写很多重复的表达式。
     * @param joinPoint
     */
    @After("declareJointPointExpression()")
    public void subAfterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("subAfterMethod：" + methodName);

    }

}
