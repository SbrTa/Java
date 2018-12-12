package com.test.controller;

import com.test.dao.Notice;
import com.test.dto.*;
import com.test.service.CounterService;
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
import java.util.*;


@Controller
public class UserController {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService){
        this.counterService = counterService;
    }

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

    @RequestMapping(value = "/loginpage")
    public String loginPage(){
        return "loginpage";
    }

    @RequestMapping(value = "/gohome")
    public String goHome(HttpSession session, Model model){
        if(session.getAttribute("user")==null){
            return "home";
        }
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals("admin")){
            List<UserPost> pending = userPostService.getPending();
            model.addAttribute("pending",pending);
            return "admin";
        }



        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        Map<Integer,List<Integer>> likers = new HashMap<Integer, List<Integer>>();
        Map<Integer,List<Integer>> dislikers = new HashMap<Integer, List<Integer>>();
        List<Counter> counters = counterService.getCounterList();
        for(Counter x:counters){
            List<Integer> lll = counterService.getIntList(x.getLiker());
            List<Integer> ddd = counterService.getIntList(x.getDisliker());
            likers.put(x.getPost(),lll);
            dislikers.put(x.getPost(),ddd);
        }
        model.addAttribute("likers",likers);
        model.addAttribute("dislikers",dislikers);
        return "user";
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
        System.out.println("user nul;l..");
        System.out.println(user);
        if(!user.getPassword().equals(password)){
            model.addAttribute("password","Incorrect user name or password");
            return "loginpage";
        }
        session.setAttribute("user",user);
        if(user.getRole().equals("admin")){
            List<UserPost> pending = userPostService.getPending();
            model.addAttribute("pending",pending);
            return "admin";
        }

        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);


        Map<Integer,List<Integer>> likers = new HashMap<Integer, List<Integer>>();
        Map<Integer,List<Integer>> dislikers = new HashMap<Integer, List<Integer>>();
        List<Counter> counters = counterService.getCounterList();
        for(Counter x:counters){
            System.out.println(x);
            List<Integer> lll = counterService.getIntList(x.getLiker());
            List<Integer> ddd = counterService.getIntList(x.getDisliker());
            likers.put(x.getPost(),lll);
            dislikers.put(x.getPost(),ddd);
        }
        model.addAttribute("likers",likers);
        model.addAttribute("dislikers",dislikers);
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
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "edituserdetailspage";
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


        Map<Integer,List<Integer>> likers = new HashMap<Integer, List<Integer>>();
        Map<Integer,List<Integer>> dislikers = new HashMap<Integer, List<Integer>>();
        List<Counter> counters = counterService.getCounterList();
        for(Counter x:counters){
            List<Integer> lll = counterService.getIntList(x.getLiker());
            List<Integer> ddd = counterService.getIntList(x.getDisliker());
            likers.put(x.getPost(),lll);
            dislikers.put(x.getPost(),ddd);
        }
        model.addAttribute("likers",likers);
        model.addAttribute("dislikers",dislikers);
        return "user";
    }

}
