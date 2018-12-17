package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Data
@NoArgsConstructor
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/signup")
    public String signUp(Model model){
        model.addAttribute(new User());
        return "signup";
    }

    @RequestMapping(value = "/loginpage")
    public String loginPage(){
        return "loginpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam("userName") String userName, @RequestParam("password") String password, Model model){
        System.out.println("in user controller : "+userName +"   "+password);
        User user = userService.getUser(userName);
        if(!user.getPassword().equals(password)){
            model.addAttribute("password","Incorrect user name or password");
            return "loginpage";
        }
        session.setAttribute("user",user);
        if(user.getRole().equals("admin")){
            model.addAttribute("pending",commonService.getPendingList());
            return "admin";
        }
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        System.out.println(user);

        return "user";
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        userService.create(user);
        userService.createUserDetails(new UserDetails(user.getUserName(),user.getName(),"","","","","","",""));
        return "home";
    }

    @RequestMapping(value = "/gohome")
    public String goHome(HttpSession session, Model model){
        if(session.getAttribute("user")==null){
            return "home";
        }
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals("admin")){
            model.addAttribute("pending",commonService.getPendingList());
            return "admin";
        }
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }



    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        System.out.println("loging out...");
        session.removeAttribute("user");
        session.invalidate();
        return "home";
    }

    @RequestMapping(value = "/edituserdetailspage")
    public String editUserDetailsPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        return "edituserdetailspage";
    }

    @RequestMapping(value = "/editUserDetails", method = RequestMethod.POST)
    public String editUserDetails(HttpSession session, Model model, UserDetails userDetails){
        User user = (User) session.getAttribute("user");
        userDetails.setUserName(user.getUserName());
        System.out.println("USER DETAILS : "+userDetails);
        userService.saveUserDetails(userDetails);

        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }
}
