package com.test.controller;

import com.test.dto.Counter;
import com.test.dto.UserPost;
import com.test.service.CommonService;
import com.test.service.CounterService;
import com.test.service.UserPostService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("adminController")
public class AdminController {
    @Autowired private UserService userService;
    @Autowired private UserPostService userPostService;
    @Autowired private CounterService counterService;
    @Autowired private CommonService commonService;

    @RequestMapping(value = "/pendingAction")
    public String pendingAction(@RequestParam("id")int id, @RequestParam("action")String action, Model model){
        UserPost post = userPostService.getPending(id);
        if(action.equals("accept")){
            userPostService.createFinal(post);
        }
        userPostService.deletePending(id);
        UserPost finalPost = userPostService.getFinal(post.getTime());
        Counter counter = new Counter(0, finalPost.getId(),"0","0");
        counterService.createCounter(counter);

        model.addAttribute("pending",commonService.getPendingList());
        return "admin";
    }
}
