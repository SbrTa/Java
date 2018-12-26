package com.roy.spring.controller;


import com.roy.spring.dto.Counter;
import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserPost;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
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
import java.util.List;
import java.util.Map;

@Controller("adminController")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/getPendingList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Pending>> getPendingList(){
        return new ResponseEntity<>(commonService.getPendingList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPendingById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Pending> getPendingById(@RequestParam int id){
        return new ResponseEntity<>(userPostService.getPending(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/createFinal", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createFinal(@RequestBody UserPost userPost){
        userPostService.createFinal(userPost);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/createCounter", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createCounter(@RequestBody Counter counter){
        counterService.createCounter(counter);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/getFinalByTime", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserPost> getFinalByTime(@RequestParam String time){
        return new ResponseEntity<>(userPostService.getFinal(time), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePending", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> deletePending(@RequestParam int id){
        userPostService.deletePending(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
