package com.roy.spring.controller;


import com.roy.spring.dto.Pending;
import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.dto.UserPost;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Controller
@RequestMapping(value = "/user/post")
public class UserPostController {

    RestTemplate template = new RestTemplate();
    String baseUrl = "http://localhost:8080";

    @RequestMapping(value = "/create")
    public String createPost(Model model, HttpSession session, @RequestParam("content") String content){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        User user = (User)session.getAttribute("user");
        Pending post = new Pending(date,user.getUserName(),user.getEmail(),content);
        //userPostService.createPending(post);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(post,headers);
        template.exchange(baseUrl+"/createPending", HttpMethod.POST, entity, String.class).getBody();
        return "redirect:/user/home";
    }

    @RequestMapping(value = "/edit")
    public String editpost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");
        UserDetails userDetails = template.exchange(baseUrl+"/getUserDetailsByUserName?userName={userName}",HttpMethod.GET,null,UserDetails.class,user.getUserName()).getBody();
        model.addAttribute("userDetails",userDetails);
        UserPost userPost = template.exchange(baseUrl+"/getUserPostById?postid={postid}",HttpMethod.GET,null,UserPost.class,postid).getBody();
        model.addAttribute("userPost",userPost);
        return "editpost";
    }

    @RequestMapping(value = "/edit/done")
    public String saveEditedPost(Model model, HttpSession session, @RequestParam("postid") int postid, @RequestParam("content") String content){
        UserPost userPost = template.exchange(baseUrl+"/getFinalById?postid={postid}",HttpMethod.GET,null,UserPost.class,postid).getBody();

        userPost.setContent(content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(userPost,headers);
        template.exchange(baseUrl+"/updateFinal", HttpMethod.POST, entity, String.class).getBody();
        return "redirect:/user/home";
    }


    @RequestMapping(value = "/delete")
    public String deletepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        UserPost userPost = template.exchange(baseUrl+"/getFinalById?postid={postid}",HttpMethod.GET,null,UserPost.class,postid).getBody();
        template.exchange(baseUrl+"/deleteFinal?postid={postid}",HttpMethod.GET,null,String.class,postid).getBody();
        template.exchange(baseUrl+"/deleteCounter?postid={postid}",HttpMethod.GET,null,String.class,postid).getBody();
        return "redirect:/user/home";
    }

}
