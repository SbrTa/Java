package com.test.controller;


import com.test.dto.Counter;
import com.test.dto.User;
import com.test.dto.UserDetails;
import com.test.dto.UserPost;
import com.test.service.CommonService;
import com.test.service.CounterService;
import com.test.service.UserPostService;
import com.test.service.UserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Controller
public class UserPostController {
    @Autowired private UserService userService;
    @Autowired private UserPostService userPostService;
    @Autowired private CounterService counterService;
    @Autowired private CommonService commonService;


    @RequestMapping(value = "/createPost")
    public String createPost(Model model, HttpSession session, @RequestParam("content") String content){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        UserPost post = new UserPost(1,date,user.getUserName(),user.getEmail(),content);
        userPostService.createPending(post);

        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }

}
