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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/createPending", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createPending(@RequestBody Pending post){
        userPostService.createPending(post);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @RequestMapping(value = "/getUserPostById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserPost> getUserPost(@RequestParam int postid){
        return new ResponseEntity<>(commonService.getUserPost(postid), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFinalById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserPost> getFinal(@RequestParam int postid){
        return new ResponseEntity<>(userPostService.getFinal(postid), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateFinal", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateFinal(@RequestBody UserPost userPost){
        userPostService.updateFinal(userPost);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFinal", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> deleteFinal(@RequestParam int postid){
        userPostService.deleteFinal(postid);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCounter", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> deleteCounter(@RequestParam int postid){
        counterService.deleteCounter(postid);
        return new ResponseEntity<>("", HttpStatus.OK);
    }






}
