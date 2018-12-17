package com.roy.spring.controller;


import com.roy.spring.dto.Pending;
import com.roy.spring.dto.User;
import com.roy.spring.dto.UserPost;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Controller
public class UserPostController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "/createPost")
    public String createPost(Model model, HttpSession session, @RequestParam("content") String content){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        Pending post = new Pending(date,user.getUserName(),user.getEmail(),content);
        userPostService.createPending(post);

        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }

}
