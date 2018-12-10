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


    @RequestMapping(value = "/likepost")
    public String likepost(Model model){
        System.out.println("LIKED......");

        User user = new User();
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "user";
    }

    @RequestMapping(value = "/dislikepost")
    public String dislikepost(Model model){
        System.out.println("DISLIKED......");

        User user = new User();
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "user";
    }

    @RequestMapping(value = "/editpost")
    public String editpost(Model model){
        System.out.println("EDIT......");

        User user = new User();
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "user";
    }

    @RequestMapping(value = "/deletepost")
    public String deletepost(Model model){
        System.out.println("DELETED......");

        User user = new User();
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        return "user";
    }


}
