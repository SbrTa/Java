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
public class CounterController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/likepost")
    public String likepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());

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

        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }

    @RequestMapping(value = "/dislikepost")
    public String dislikepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());

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

        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }

    @RequestMapping(value = "/editpost")
    public String editpost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("userPost",commonService.getUserPost(postid));
        return "editpost";
    }

    @RequestMapping(value = "/saveEditedPost")
    public String saveEditedPost(Model model, HttpSession session, @RequestParam("postid") int postid, @RequestParam("content") String content){
        UserPost userPost = userPostService.getFinal(postid);
        userPost.setContent(content);
        userPostService.updateFinal(userPost);

        User user = (User) session.getAttribute("user");
        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }


    @RequestMapping(value = "/deletepost")
    public String deletepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        UserPost deletePost = userPostService.getFinal(postid);
        userPostService.deleteFinal(postid);
        counterService.deleteCounter(postid);

        model.addAttribute("userDetails",commonService.getUserDetails(user.getUserName()));
        model.addAttribute("finalPost",commonService.getPostList());
        model.addAttribute("likers",commonService.getLikers());
        model.addAttribute("dislikers",commonService.getDislikers());
        return "user";
    }

}
