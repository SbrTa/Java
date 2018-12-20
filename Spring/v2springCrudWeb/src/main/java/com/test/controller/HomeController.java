package com.test.controller;

import com.test.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession session){
        return "home";
    }

    /*@RequestMapping(value = "/testjson")
    public @ResponseBody
    User Json(){
        User user = new User(555,"Jackson","json@jackson.com","jsonn","noPass","Anonymus");
        System.out.println("json test : "+user);
        return user;
    }*/


}
