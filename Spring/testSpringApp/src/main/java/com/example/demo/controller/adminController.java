package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class adminController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("-----------index-------------");
        model.addAttribute("myMessage","Hey we have run it successfully.....");
        return "index";
    }






}
