package com.roy.spring.controller;


import com.roy.spring.dto.Counter;
import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserPost;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller("adminController")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

/*    @RequestMapping(value = "/home")
    public String home(Model model, HttpSession session){
        model.addAttribute("pending",commonService.getPendingList());
        return "admin";
    }

    @RequestMapping(value = "/pendingAction")
    public String pendingAction(@RequestParam("id")int id, @RequestParam("action")String action, Model model){
        Pending post = userPostService.getPending(id);
        UserPost userPost = new UserPost(post.getTime(),post.getUserName(),post.getEmail(),post.getContent());
        if(action.equals("accept")){
            userPostService.createFinal(userPost);
            UserPost finalPost = userPostService.getFinal(post.getTime());
            Counter counter = new Counter(finalPost.getId(),"0","0");
            counterService.createCounter(counter);
        }
        userPostService.deletePending(id);
        return "redirect:/admin/home";
    }*/
}
