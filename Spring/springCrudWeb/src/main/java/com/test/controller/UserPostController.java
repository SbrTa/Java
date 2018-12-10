package com.test.controller;


import com.test.dto.User;
import com.test.dto.UserDetails;
import com.test.dto.UserPost;
import com.test.service.UserPostService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserPostController {

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


    @RequestMapping(value = "/createPost")
    public String createPost(HttpSession session, @RequestParam("content") String content){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        UserPost post = new UserPost(1,date,user.getUserName(),user.getEmail(),content);

        System.out.println(post);

        userPostService.createPending(post);

        return "user";
    }




}
