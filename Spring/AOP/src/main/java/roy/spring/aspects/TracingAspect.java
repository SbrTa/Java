package roy.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class TracingAspect {


    /*@Before("execution(* roy.spring.service.*.*(..))")
    public void entering(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+" method STARTED..");
    }

    @After("execution(* roy.spring.service.*.*(..))")
    public void exit(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+" method ENDED..");
    }*/

    /*@Around("execution(* roy.spring.service.*.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        String name = joinPoint.getStaticPart().getSignature().toString();
        System.out.println(name+" method STARTED..");
        joinPoint.proceed();
        System.out.println(name+" method ENDED..");
    }*/

    @Around("AopArchitecture.Service()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        String name = joinPoint.getStaticPart().getSignature().toString();
        System.out.println(name+" method STARTED..");
        joinPoint.proceed();
        System.out.println(name+" method ENDED..");
    }

}
