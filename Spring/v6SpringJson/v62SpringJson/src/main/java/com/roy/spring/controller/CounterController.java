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

    @RequestMapping(value = "/getCounterById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Counter> getCounterById(@RequestParam int postid){
        return new ResponseEntity<>(counterService.getCounter(postid), HttpStatus.OK);
    }

    @RequestMapping(value = "/getIntListLiker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Integer>> getIntListLiker(@RequestBody String liker){
        return new ResponseEntity<>(counterService.getIntList(liker),HttpStatus.OK);
    }

    @RequestMapping(value = "/getIntListDisLiker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Integer>> getIntListDisLiker(@RequestBody String disLiker){
        return new ResponseEntity<>(counterService.getIntList(disLiker),HttpStatus.OK);
    }


    @RequestMapping(value = "/removeLikerOrDisLiker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> removeLikerOrDisLiker(@RequestBody Map<String,Object> data){
        List<Integer> exist = (List<Integer>) data.get("exist");
        int id = (int) data.get("id");
        return new ResponseEntity<>(counterService.remove(exist, id),HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCounter", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String > updateCounter(@RequestBody Counter counter){
        counterService.updateCounter(counter);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @RequestMapping(value = "/addLikerOrDisLiker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> addLikerOrDisLiker(@RequestBody Map<String,Object> data){
        List<Integer> exist = (List<Integer>) data.get("exist");
        String counterStr = (String) data.get("counterStr");
        int id = (int) data.get("id");
        System.out.println("still okay........");
        return new ResponseEntity<>(counterService.add(exist, counterStr, id),HttpStatus.OK);
    }
}
