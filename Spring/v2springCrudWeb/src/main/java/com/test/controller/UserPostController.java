package com.test.controller;


import com.test.dto.Counter;
import com.test.dto.User;
import com.test.dto.UserDetails;
import com.test.dto.UserPost;
import com.test.service.CounterService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService){
        this.counterService = counterService;
    }


    @RequestMapping(value = "/createPost")
    public String createPost(Model model, HttpSession session, @RequestParam("content") String content){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        UserPost post = new UserPost(1,date,user.getUserName(),user.getEmail(),content);

        System.out.println(post);

        userPostService.createPending(post);

        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
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
