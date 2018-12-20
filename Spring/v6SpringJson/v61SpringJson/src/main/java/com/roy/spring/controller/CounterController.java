package com.roy.spring.controller;

import com.roy.spring.dto.Counter;
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
import java.util.List;

@Data
@NoArgsConstructor
@Controller("counterController")
@RequestMapping(value = "/user/post")
public class CounterController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/like")
    public String likepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");

        Counter counter = counterService.getCounter(postid);
        List<Integer> liker = counterService.getIntList(counter.getLiker());
        List<Integer> disliker = counterService.getIntList(counter.getDisliker());

        if(liker.contains(user.getId())){
            String newLiker = counterService.remove(liker, user.getId());
            counter.setLiker(newLiker);
            counterService.updateCounter(counter);
        }
        else if(disliker.contains(user.getId())){
            String newDisliker = counterService.remove(disliker, user.getId());
            counter.setDisliker(newDisliker);
            String newLiker = counterService.add(liker, counter.getLiker(), user.getId());
            counter.setLiker(newLiker);
            counterService.updateCounter(counter);
        }
        else{
            String newLiker = counterService.add(liker, counter.getLiker(), user.getId());
            System.out.println(newLiker);
            counter.setLiker(newLiker);
            counterService.updateCounter(counter);
        }

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/dislike")
    public String dislikepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");

        Counter counter = counterService.getCounter(postid);
        List<Integer> liker = counterService.getIntList(counter.getLiker());
        List<Integer> disliker = counterService.getIntList(counter.getDisliker());

        if(disliker.contains(user.getId())){
            String newDisliker = counterService.remove(disliker, user.getId());
            counter.setDisliker(newDisliker);
            counterService.updateCounter(counter);
        }
        else if(liker.contains(user.getId())){
            String newLiker = counterService.remove(liker, user.getId());
            counter.setLiker(newLiker);
            String newDisliker = counterService.add(disliker, counter.getDisliker(), user.getId());
            counter.setDisliker(newDisliker);
            counterService.updateCounter(counter);
        }
        else{
            String newDisliker = counterService.add(disliker, counter.getDisliker(), user.getId());
            counter.setDisliker(newDisliker);
            counterService.updateCounter(counter);
        }

        return "redirect:/user/home";
    }



}
