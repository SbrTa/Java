package com.test.controller;

import com.test.dao.Notice;
import com.test.dto.User;
import com.test.service.NoticesService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }


    @RequestMapping(value = "/signup")
    public String signUp(Model model){
        model.addAttribute(new User());
        return "signup";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid User user, BindingResult result){

        if (result.hasErrors()){
            return "signup";
        }

        userService.create(user);

        return "home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model){
        System.out.println(userName);
        System.out.println(password);
        User user = userService.getUser(userName);
        System.out.println(user);

        if(!user.getPassword().equals(password)){
            model.addAttribute("password","Incorrect user name or password");
            return "home";
        }

        if(user.getRole().equals("admin")){

            return "admin";
        }

        return "user";
    }




}
