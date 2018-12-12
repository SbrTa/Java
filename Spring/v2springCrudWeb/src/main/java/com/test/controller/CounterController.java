package com.test.controller;

import com.test.dto.*;
import com.test.service.CounterService;
import com.test.service.UserPostService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("counterController")
public class CounterController {
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



    @RequestMapping(value = "/likepost")
    public String likepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);

        System.out.println("LIKED......"+postid+"  "+user.getId());


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

    @RequestMapping(value = "/dislikepost")
    public String dislikepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        List<UserPost> finalPost = userPostService.getFinal();
        model.addAttribute("finalPost",finalPost);
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);

        System.out.println("DIS LIKED......"+postid+"  "+user.getId());


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

    @RequestMapping(value = "/editpost")
    public String editpost(Model model, HttpSession session, @RequestParam("postid") int postid){
        System.out.println("EDIT......");
        User user = (User) session.getAttribute("user");
        UserDetails userDetails = userService.getUserDetails(user.getUserName());
        model.addAttribute("userDetails",userDetails);
        UserPost userPost = userPostService.getFinal(postid);

        if(!userPost.getUserName().equals(user.getUserName())){
            List<UserPost> finalPost = userPostService.getFinal();
            model.addAttribute("finalPost",finalPost);
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

        model.addAttribute("userPost",userPost);

        return "editpost";
    }

    @RequestMapping(value = "/saveEditedPost")
    public String saveEditedPost(Model model, HttpSession session, @RequestParam("postid") int postid, @RequestParam("content") String content){
        System.out.println("Save edited post......"+postid+" \n "+content);
        UserPost userPost = userPostService.getFinal(postid);
        userPost.setContent(content);
        userPostService.updateFinal(userPost);

        User user = (User) session.getAttribute("user");
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


    @RequestMapping(value = "/deletepost")
    public String deletepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        System.out.println("DELETED......");

        User user = (User) session.getAttribute("user");
        UserPost deletePost = userPostService.getFinal(postid);

        if(deletePost.getUserName().equals(user.getUserName())){
            userPostService.deleteFinal(postid);
            counterService.deleteCounter(postid);
        }else {
            System.out.println("user can delete only his/her own post.");
        }

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
