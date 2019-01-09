package roy.spring.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class TracingAspect {


    @Before("execution(void doIt())")
    public void entering(){
        System.out.println("\n\n\nin kamla: Do It...");
    }

    @After("execution(void doIt())")
    public void exit(){
        System.out.println("in kamla: Done...");
    }
}
