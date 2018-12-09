package com.test.controller;

import com.test.dao.Notice;
import com.test.dto.User;
import com.test.dto.UserDetails;
import com.test.dto.UserPost;
import com.test.service.NoticesService;
import com.test.service.UserPostService;
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


    private UserPostService userPostService;

    @Autowired
    public void setUserPostService(UserPostService userPostService){
        this.userPostService = userPostService;
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
        userService.createUserDetails(new UserDetails(1,user.getUserName(),user.getName(),"","","","","","",""));
        //userService.createUserDetails(new UserDetails(1,user.getUserName(),user.getName(),"dob","blood","gender","city","contact","relation","bio"));

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
            List<UserPost> pending = userPostService.getPending();
            model.addAttribute("pending",pending);
            return "admin";
        }

        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "user";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        System.out.println("loging out...");
        return "home";
    }


    @RequestMapping(value = "/editUserDetails", method = RequestMethod.POST)
    public String editUserDetails(HttpSession session, Model model, UserDetails userDetails){
        System.out.println("edit user details :");
        User user = (User) session.getAttribute("user");
        userDetails.setUserName(user.getUserName());
        System.out.println(userDetails);
        userService.saveUserDetails(userDetails);

        userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        return "user";
    }

}
