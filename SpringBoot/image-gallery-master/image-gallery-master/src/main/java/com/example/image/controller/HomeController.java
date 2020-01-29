package com.example.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/app")
    public String index(){
        return "redirect:/app/movie";
    }
}
