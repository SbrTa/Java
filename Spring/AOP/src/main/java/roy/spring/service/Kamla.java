package roy.spring.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Kamla {

    public void doIt(){
        System.out.println("Kamla is sleeping now.  Come later..");
        Date date = new Date();
        System.out.println(date);
        //date.setTime(00);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        System.out.println(date);
    }
}
