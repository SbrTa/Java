package com.test.controller;

import com.test.dto.UserPost;
import com.test.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("adminController")
public class AdminController {

    private UserPostService userPostService;

    @Autowired
    public void setUserPostService(UserPostService userPostService){
        this.userPostService=userPostService;
    }

    @RequestMapping(value = "/pendingAction")
    public String pendingAction(@RequestParam("id")int id, @RequestParam("action")String action){
        System.out.println(id+action);

        if(action.equals("accept")){
            UserPost post = userPostService.getPending(id);
            System.out.println(post);
        }else{

        }
        return "admin";
    }
}
