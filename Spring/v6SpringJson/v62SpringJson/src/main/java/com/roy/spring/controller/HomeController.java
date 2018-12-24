package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/")
    public String getHome(){
        return "index";
    }

    public static String encodePasswordWithBCrypt(String plainPassword){
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> doCreate(@RequestBody User user){
        System.out.println("Exchange = "+user);
        user.setPassword(encodePasswordWithBCrypt(user.getPassword()));
        userService.create(user);
        userService.createUserDetails(new UserDetails(user.getUserName(),user.getName(),"","","","","","",""));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserByUserName", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName){
        User user = userService.getUser(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/testBack", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> testJson(@RequestBody User user1) throws IOException{
        //System.out.println("in back end test json.."+id);
        /*String JSON = IOUtils.toString(request.getInputStream(),"UTF-8");
        System.out.println("request entity : "+JSON);*/
        System.out.println(user1);
        User user = new User("Jackson","jackson@json.net","jsonn","password","Anonymus",true);
        user.setId(555);
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
