package roy.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import roy.spring.service.Kamla;

@Controller
public class Home {

    @Autowired
    Kamla kamla;

    @RequestMapping("/")
    public String inHome(){
        System.out.println("In home now.....");
        kamla.doIt();
        return "home";
    }
}
