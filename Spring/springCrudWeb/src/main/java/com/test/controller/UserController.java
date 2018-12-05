package com.test.controller;

import com.test.dao.Notice;
import com.test.dto.User;
import com.test.dto.UserPost;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public String login(HttpSession session, @RequestParam("userName") String userName, @RequestParam("password") String password, Model model){
        System.out.println(userName);
        System.out.println(password);
        User user = userService.getUser(userName);
        System.out.println(user);
        session.setAttribute("user",user);
        if(!user.getPassword().equals(password)){
            model.addAttribute("password","Incorrect user name or password");
            return "home";
        }

        if(user.getRole().equals("admin")){

            return "admin";
        }

        return "user";
    }

    @RequestMapping(value = "/createPost")
    public String createPost(HttpSession session, @RequestParam("content") String content){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        UserPost post = new UserPost(1,date,user.getUserName(),user.getEmail(),content);

        System.out.println(post);

        return "user";
    }




}
