package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.dto.UserPost;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {
    RestTemplate template = new RestTemplate();
    String baseUrl = "http://localhost:8080";

    @RequestMapping(value = "/home")
    public String home(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println("user home processing....");
        UserDetails userDetails = template.exchange(baseUrl+"/getUserDetailsByUserName?userName={userName}",HttpMethod.GET,null,UserDetails.class,user.getUserName()).getBody();
        model.addAttribute("userDetails",userDetails);
        List<UserPost> finalPost = template.exchange(baseUrl + "/getPostList", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserPost>>(){}).getBody();
        model.addAttribute("finalPost",finalPost);
        Map<Integer,List<Integer>> likers = template.exchange(baseUrl + "/getLikers", HttpMethod.GET, null, new ParameterizedTypeReference<Map<Integer,List<Integer>>>(){}).getBody();
        model.addAttribute("likers",likers);
        Map<Integer,List<Integer>> disLikers = template.exchange(baseUrl + "/getDislikers", HttpMethod.GET, null, new ParameterizedTypeReference<Map<Integer,List<Integer>>>(){}).getBody();
        model.addAttribute("dislikers",disLikers);
        System.out.println(user);
        return "user";
    }

    @RequestMapping(value = "/details/edit")
    public String editUserDetailsPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        UserDetails userDetails = template.exchange(baseUrl+"/getUserDetailsByUserName?userName={userName}",HttpMethod.GET,null,UserDetails.class,user.getUserName()).getBody();
        model.addAttribute("userDetails",userDetails);
        return "edituserdetailspage";
    }

    @RequestMapping(value = "/details/edit/done", method = RequestMethod.POST)
    public String editUserDetails(HttpSession session, Model model, UserDetails userDetails){
        User user = (User) session.getAttribute("user");
        userDetails.setUserName(user.getUserName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(userDetails,headers);
        template.exchange(baseUrl+"/saveUserDetails",HttpMethod.POST,entity,UserDetails.class).getBody();
        return "redirect:/user/home";
    }
}
