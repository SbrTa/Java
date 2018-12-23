package com.roy.spring.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@Controller
//@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/getUserDetailsByUserName", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDetails> getUserDetailsByUserName(@RequestParam String userName){
        return new ResponseEntity<>(commonService.getUserDetails(userName), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPostList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserPost>> getPostList(){
        return new ResponseEntity<>(commonService.getPostList(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getLikers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<Integer,List<Integer>>> getLikers(){
        return new ResponseEntity<>(commonService.getLikers(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getDislikers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<Integer,List<Integer>>> getDislikers(){
        return new ResponseEntity<>(commonService.getDislikers(),HttpStatus.OK);
    }

    @RequestMapping(value = "/saveUserDetails", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDetails> saveUserDetails(@RequestBody UserDetails userDetails){
        userService.saveUserDetails(userDetails);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

}
