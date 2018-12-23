package com.roy.spring.controller;


import com.roy.spring.dto.Counter;
import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserPost;
import org.springframework.core.ParameterizedTypeReference;
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
import java.util.List;

@Controller("adminController")
@RequestMapping(value = "/admin")
public class AdminController {

    RestTemplate template = new RestTemplate();
    String baseUrl = "http://localhost:8080";

    @RequestMapping(value = "/home")
    public String home(Model model, HttpSession session){
        List<Pending> pendings = template.exchange(baseUrl + "/getPendingList", HttpMethod.GET, null, new ParameterizedTypeReference<List<Pending>>(){}).getBody();
        model.addAttribute("pending",pendings);
        return "admin";
    }

    @RequestMapping(value = "/pendingAction")
    public String pendingAction(@RequestParam("id")int id, @RequestParam("action")String action, Model model){
        Pending post = template.exchange(baseUrl + "/getPendingById?id={id}", HttpMethod.GET, null, Pending.class, id).getBody();
        UserPost userPost = new UserPost(post.getTime(),post.getUserName(),post.getEmail(),post.getContent());
        if(action.equals("accept")){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(userPost,headers);
            template.exchange(baseUrl + "/createFinal", HttpMethod.POST, entity, String.class).getBody();

            UserPost finalPost = template.exchange(baseUrl + "/getFinalByTime?time={time}", HttpMethod.GET, null, UserPost.class,post.getTime()).getBody();

            Counter counter = new Counter(finalPost.getId(),"0","0");
            HttpEntity entity1 = new HttpEntity(counter,headers);
            template.exchange(baseUrl + "/createCounter", HttpMethod.POST, entity1, String.class).getBody();
        }
        template.exchange(baseUrl + "/deletePending?id={id}", HttpMethod.GET, null, String.class,id).getBody();

        return "redirect:/admin/home";
    }
}
