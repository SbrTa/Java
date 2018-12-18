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
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "/home")
    public String home(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        System.out.println(user);
        return "user";
    }

    @RequestMapping(value = "/details/edit")
    public String editUserDetailsPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        return "edituserdetailspage";
    }

    @RequestMapping(value = "/details/edit/done", method = RequestMethod.POST)
    public String editUserDetails(HttpSession session, Model model, UserDetails userDetails){
        User user = (User) session.getAttribute("user");
        userDetails.setUserName(user.getUserName());
        System.out.println("USER DETAILS : "+userDetails);
        userService.saveUserDetails(userDetails);
        return "redirect:/user/home";
    }
}
