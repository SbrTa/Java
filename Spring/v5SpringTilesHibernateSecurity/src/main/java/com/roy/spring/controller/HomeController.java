package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

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
        if(user.getRole().equals("ROLE_ADMIN")){
            model.addAttribute("pending",commonService.getPendingList());
            return "admin";
        }
        return "redirect:/user/home";
    }

    @RequestMapping(value = "/signup")
    public String signUp(Model model){
        System.out.println("sign up now..");
        model.addAttribute(new User());
        return "signup";
    }

    /*@RequestMapping(value = "/signup")
    public String signUp(){
        System.out.println("sign up now..");
        return "signup";
    }*/

    public static String encodePasswordWithBCrypt(String plainPassword){
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        System.out.println(user.getPassword()+" => "+encodePasswordWithBCrypt(user.getPassword()));
        user.setPassword(encodePasswordWithBCrypt(user.getPassword()));
        userService.create(user);
        userService.createUserDetails(new UserDetails(user.getUserName(),user.getName(),"","","","","","",""));
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,
            @RequestParam(value = "error", required = false) String error){
        System.out.println("login method.............");
        if (error!=null){
            model.addAttribute("error","Incorrect user name or password. Plz try again.");
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
    public String loginTest(Model model, Principal principal){
        System.out.println("Login Test :  Loged in successfullty...............");
        System.out.println(principal.getName());
        return "redirect:/login/done";
    }

    @RequestMapping(value = "/logintest/done")
    public String loginTest(Model model){
        System.out.println("ok.. logged in.....");
        model.addAttribute(new User());
        return "signup";
    }

    /*@RequestMapping(value = "/login/done", method = RequestMethod.POST)
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
    }*/

    @RequestMapping(value = "/login/done")
    public String login(HttpSession session, Principal principal){
        System.out.println("in user controller : "+principal.getName());
        User user = userService.getUser(principal.getName());
        session.setAttribute("user",user);
        if(user.getRole().equals("ROLE_ADMIN")){
            return "redirect:/admin/home";
        }
        return "redirect:/user/home";
    }

}
