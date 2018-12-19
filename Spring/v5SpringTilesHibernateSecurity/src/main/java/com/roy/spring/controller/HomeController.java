package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession session){
        return "home";
    }


    @RequestMapping(value = "/gohome")
    public String goHome(HttpSession session, Model model){
        if(session.getAttribute("user")==null){
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals("admin")){
            model.addAttribute("pending",commonService.getPendingList());
            return "admin";
        }
        return "redirect:/user/home";
    }

    @RequestMapping(value = "/signup")
    public String signUp(Model model, @ModelAttribute("user") User user){
        System.out.println("sign up now..");
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());


        model.addAttribute(new User());
        return "signup";
    }

    /*@RequestMapping(value = "/signup")
    public String signUp(){
        System.out.println("sign up now..");
        return "signup";
    }*/

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        userService.create(user);
        userService.createUserDetails(new UserDetails(user.getUserName(),user.getName(),"","","","","","",""));
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,
            @RequestParam(value = "error", required = false) String error){
        System.out.println("login method.............");
        if (error!=null){
            model.addAttribute("error","invalid username and password");
        }

        return "loginpage";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        System.out.println("loging out...");
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/logintest")
    public String loginTest(){
        System.out.println("Login Test :  Loged in successfullty...............");
        return "redirect:/signup";
    }

    @RequestMapping(value = "/login/done", method = RequestMethod.POST)
    public String login(HttpSession session, Model model,
                        @RequestParam("userName") String userName,
                        @RequestParam("password") String password){
        System.out.println("in user controller : "+userName +"   "+password);
        User user = userService.getUser(userName);
        if(!user.getPassword().equals(password)){
            model.addAttribute("password","Incorrect user name or password. Plz try again.");
            return "loginpage";
        }
        session.setAttribute("user",user);
        if(user.getRole().equals("admin")){
            return "redirect:/admin/home";
        }
        return "redirect:/user/home";
    }


}
