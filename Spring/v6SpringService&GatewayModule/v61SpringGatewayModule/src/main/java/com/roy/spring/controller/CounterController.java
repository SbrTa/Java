package com.roy.spring.controller;

import com.roy.spring.dto.Counter;
import com.roy.spring.dto.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Controller("counterController")
@RequestMapping(value = "/user/post")
public class CounterController {
    RestTemplate template = new RestTemplate();
    String baseUrl = "http://localhost:8080";

    @RequestMapping(value = "/like")
    public String likepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");

        Counter counter = template.exchange(baseUrl+"/getCounterById?postid={postid}", HttpMethod.GET,null,Counter.class,postid).getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(counter.getLiker(),headers);
        List<Integer> liker = template.exchange(baseUrl + "/getIntListLikerOrDisLiker", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Integer>>(){}).getBody();
        HttpEntity entity1 = new HttpEntity(counter.getDisliker(),headers);
        List<Integer> disliker = template.exchange(baseUrl + "/getIntListLikerOrDisLiker", HttpMethod.POST, entity1, new ParameterizedTypeReference<List<Integer>>(){}).getBody();

        if(liker.contains(user.getId())){
            System.out.println("like if");
            Map<String,Object> data = new HashMap<>();
            data.put("exist",liker);
            data.put("id",user.getId());
            HttpEntity entity2 = new HttpEntity(data,headers);
            String newLiker = template.exchange(baseUrl+"/removeLikerOrDisLiker",HttpMethod.POST,entity2,String.class).getBody();
            System.out.println("1111111111111111");

            counter.setLiker(newLiker);
            HttpEntity entity3 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("2222222222222222");
        }
        else if(disliker.contains(user.getId())){
            System.out.println("like else if");
            Map<String,Object> data = new HashMap<>();
            data.put("exist",disliker);
            data.put("id",user.getId());
            HttpEntity entity2 = new HttpEntity(data,headers);
            String newDisliker = template.exchange(baseUrl+"/removeLikerOrDisLiker",HttpMethod.POST,entity2,String.class).getBody();
            System.out.println("1111111111111111");

            counter.setDisliker(newDisliker);
            Map<String,Object> data2 = new HashMap<>();
            data2.put("exist",liker);
            data2.put("counterStr",counter.getLiker());
            data2.put("id",user.getId());
            HttpEntity entity3 = new HttpEntity(data2,headers);
            String newLiker = template.exchange(baseUrl+"/addLikerOrDisLiker",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("2222222222222222");

            counter.setLiker(newLiker);
            HttpEntity entity4 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity4,String.class).getBody();
            System.out.println("333333333333333");
        }
        else{
            System.out.println("like else");
            Map<String,Object> data2 = new HashMap<>();
            data2.put("exist",liker);
            data2.put("counterStr",counter.getLiker());
            data2.put("id",user.getId());
            HttpEntity entity3 = new HttpEntity(data2,headers);
            String newLiker = template.exchange(baseUrl+"/addLikerOrDisLiker",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("1111111111111111");

            counter.setLiker(newLiker);
            HttpEntity entity4 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity4,String.class).getBody();
            System.out.println("2222222222222222");
        }

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/dislike")
    public String dislikepost(Model model, HttpSession session, @RequestParam("postid") int postid){
        User user = (User) session.getAttribute("user");

        Counter counter = template.exchange(baseUrl+"/getCounterById?postid={postid}", HttpMethod.GET,null,Counter.class,postid).getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(counter.getLiker(),headers);
        List<Integer> liker = template.exchange(baseUrl + "/getIntListLikerOrDisLiker", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Integer>>(){}).getBody();
        HttpEntity entity1 = new HttpEntity(counter.getDisliker(),headers);
        List<Integer> disliker = template.exchange(baseUrl + "/getIntListLikerOrDisLiker", HttpMethod.POST, entity1, new ParameterizedTypeReference<List<Integer>>(){}).getBody();

        if(disliker.contains(user.getId())){
            System.out.println("dislike if");
            Map<String,Object> data = new HashMap<>();
            data.put("exist",disliker);
            data.put("id",user.getId());
            HttpEntity entity2 = new HttpEntity(data,headers);
            String newDisliker = template.exchange(baseUrl+"/removeLikerOrDisLiker",HttpMethod.POST,entity2,String.class).getBody();
            System.out.println("1111111111111111");

            counter.setDisliker(newDisliker);
            HttpEntity entity3 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("2222222222222222");
        }
        else if(liker.contains(user.getId())){
            System.out.println("dislike else if");
            Map<String,Object> data = new HashMap<>();
            data.put("exist",liker);
            data.put("id",user.getId());
            HttpEntity entity2 = new HttpEntity(data,headers);
            String newLiker = template.exchange(baseUrl+"/removeLikerOrDisLiker",HttpMethod.POST,entity2,String.class).getBody();
            System.out.println("111111111111111");

            counter.setLiker(newLiker);
            Map<String,Object> data2 = new HashMap<>();
            data2.put("exist",disliker);
            data2.put("counterStr",counter.getDisliker());
            data2.put("id",user.getId());
            HttpEntity entity3 = new HttpEntity(data2,headers);
            String newDisliker = template.exchange(baseUrl+"/addLikerOrDisLiker",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("2222222222222");

            counter.setDisliker(newDisliker);
            HttpEntity entity4 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity4,String.class).getBody();
            System.out.println("333333333333333333");
        }
        else{
            System.out.println("dislike else");
            Map<String,Object> data = new HashMap<>();
            data.put("exist",disliker);
            data.put("counterStr",counter.getDisliker());
            data.put("id",user.getId());
            HttpEntity entity3 = new HttpEntity(data,headers);
            String newDisliker = template.exchange(baseUrl+"/addLikerOrDisLiker",HttpMethod.POST,entity3,String.class).getBody();
            System.out.println("111111111111111");

            counter.setDisliker(newDisliker);
            HttpEntity entity4 = new HttpEntity(counter,headers);
            template.exchange(baseUrl+"/updateCounter",HttpMethod.POST,entity4,String.class).getBody();;
            System.out.println("222222222222");
        }

        return "redirect:/user/home";
    }



}
