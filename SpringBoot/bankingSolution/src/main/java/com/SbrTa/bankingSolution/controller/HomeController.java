package com.SbrTa.bankingSolution.controller;


import com.SbrTa.bankingSolution.dao.RoleDao;
import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import com.SbrTa.bankingSolution.domain.SavingsAccount;
import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.domain.security.UserRole;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;


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
        if (userService.checkUsernameExists(user.getUsername())
                && userService.checkEmailExists(user.getEmail())){
            model.addAttribute("usernameExists",true);
            model.addAttribute("emailExists",true);
            return "signup";
        }
        if(userService.checkUsernameExists(user.getUsername())){
            model.addAttribute("usernameExists",true);
            return "signup";
        }
        if (userService.checkEmailExists(user.getEmail())){
            model.addAttribute("emailExists",true);
            return "signup";
        }

        Set< UserRole > userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
        userService.createUser(user, userRoles);
        return "redirect:/";
    }

    @RequestMapping("/userFront")
    public String userFront(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();
        model.addAttribute("primaryAccount",primaryAccount);
        model.addAttribute("savingsAccount",savingsAccount);
        return "userFront";
    }
}
