package com.SbrTa.bankingSolution.controller;


import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/signup")
    public String signUp(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String doSignUp(@ModelAttribute("user") User user, Model model){
        System.out.println(user);
        if (userService.checkUserNameExists(user.getUserName())
                && userService.checkEmailExists(user.getEmail())){
            model.addAttribute("userNameExists",true);
            model.addAttribute("emailExists",true);
            return "signup";
        }
        if(userService.checkUserNameExists(user.getUserName())){
            model.addAttribute("userNameExists",true);
            return "signup";
        }
        if (userService.checkEmailExists(user.getEmail())){
            model.addAttribute("emailExists",true);
            return "signup";
        }

        userService.save(user);
        return "redirect:/";
    }
}
