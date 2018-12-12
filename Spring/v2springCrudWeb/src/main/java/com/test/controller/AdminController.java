package com.test.controller;

import com.test.dto.Counter;
import com.test.dto.UserPost;
import com.test.service.CounterService;
import com.test.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("adminController")
public class AdminController {

    private UserPostService userPostService;

    @Autowired
    public void setUserPostService(UserPostService userPostService){
        this.userPostService=userPostService;
    }

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService){
        this.counterService = counterService;
    }

    @RequestMapping(value = "/pendingAction")
    public String pendingAction(@RequestParam("id")int id, @RequestParam("action")String action, Model model){
        System.out.println(id+action);
        UserPost post = userPostService.getPending(id);
        if(action.equals("accept")){
            userPostService.createFinal(post);
            System.out.println(post);
        }
        userPostService.deletePending(id);

        UserPost finalPost = userPostService.getFinal(post.getTime());
        System.out.println("final post === "+finalPost);

        Counter counter = new Counter(0, finalPost.getId(),"0","0");
        counterService.createCounter(counter);

        List<UserPost> pending = userPostService.getPending();
        model.addAttribute("pending",pending);
        return "admin";
    }
}
