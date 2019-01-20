package roy.spring.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class AopArchitecture {

    @Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
    public void Service(){ }


}
