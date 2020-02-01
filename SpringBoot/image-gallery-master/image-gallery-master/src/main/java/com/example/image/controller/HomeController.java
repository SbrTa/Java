package com.example.image.controller;

import com.example.image.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/app";
    }

    @GetMapping("/app")
    public String welcome(){
        return "Welcome";
    }
    @PostMapping("/app")
    public String welcomePOST(){
        userService.createUser();
        return "redirect:/app/userChoice";
    }

}
