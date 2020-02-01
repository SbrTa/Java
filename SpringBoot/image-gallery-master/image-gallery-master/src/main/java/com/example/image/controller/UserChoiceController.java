package com.example.image.controller;

import com.example.image.service.UserChoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class UserChoiceController {
    UserChoiceService userChoiceService;

    public UserChoiceController(UserChoiceService userChoiceService) {
        this.userChoiceService = userChoiceService;
    }

    @GetMapping("/userChoice")
    public String getUserChoice(){
        return "UsersChoice";
    }

    @PostMapping("/userChoice")
    public String saveUserChoice(@RequestParam("talkativeLevel") int talkativeLevel,
                             @RequestParam("findFaultLevel") int findFaultLevel,
                             @RequestParam("thoroughJobLevel") int thoroughJobLevel,
                             @RequestParam("depressionLevel") int depressionLevel)  {
        userChoiceService.saveUserChoice(talkativeLevel,findFaultLevel,thoroughJobLevel,depressionLevel);
        return "redirect:/app/movie/gallery";
    }
}
