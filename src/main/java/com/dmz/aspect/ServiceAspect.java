package com.dmz.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 类定义上需要增加@Aspect和@Component注解  切面类做的是方法的是方法的增强
 */
@Aspect //
@Component//在容器中注册一个组件
public class ServiceAspect {

    @Pointcut("execution(* com..*(..))")
    private void servicePointcut(){}

    //@Before("servicePointcut()")//pointcut-ref="servicePointcut"
    @Before("execution(* com..service..*(..))") //pointcut="切入点表达式"
    public void mybefore(){
        System.out.println("正道的光");
    }

    /**
     * 类似于finally，是一定可以执行到的
     */
    @After("servicePointcut()")
    public void myafter(){
        System.out.println("照在大地上");
    }

    /**
     * param 参数ProceedingjoinPoint
     * return 返回值Object
     */
    @Around("servicePointcut()")
    public Object myaround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("around的前面");
        System.out.println(joinPoint.toLongString());
        Object proceed = joinPoint.proceed();

        System.out.println("around的后面");
        return proceed;
    }

    /**
     * @param objectz 需要告诉aspectj，以形参中的object来接收返回值
     */
    @AfterReturning(value = "servicePointcut()",returning = "objectz")
    public void myafterReturning(Object objectz){

        System.out.println("委托类执行return结果：" + objectz);
    }

//    public void myafterThrowing(Exception exception){
    @AfterThrowing(value = "servicePointcut()",throwing = "exception")
    public void myafterThrowing(Throwable exception){
        System.out.println("抛出了异常：" + exception.getMessage());
    }

}
