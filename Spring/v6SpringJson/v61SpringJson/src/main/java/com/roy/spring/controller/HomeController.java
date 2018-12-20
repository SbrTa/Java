package com.roy.spring.controller;

import com.roy.spring.dto.User;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.service.CommonService;
import com.roy.spring.service.CounterService;
import com.roy.spring.service.UserPostService;
import com.roy.spring.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession session){
        if(session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if(user.getRole().equals("ROLE_ADMIN")){
                return "redirect:/admin/home";
            }
            return "redirect:/user/home";
        }
        return "home";
    }


    @RequestMapping(value = "/gohome")
    public String goHome(HttpSession session, Model model){
        return "redirect:/";
    }

    @RequestMapping(value = "/signup")
    public String signUp(HttpSession session, Model model){
        if(session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if(user.getRole().equals("ROLE_ADMIN")){
                return "redirect:/admin/home";
            }
            return "redirect:/user/home";
        }
        System.out.println("sign up now..");
        model.addAttribute(new User());
        return "signup";
    }

    /*@RequestMapping(value = "/signup")
    public String signUp(){
        System.out.println("sign up now..");
        return "signup";
    }*/

    public static String encodePasswordWithBCrypt(String plainPassword){
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        System.out.println(user.getPassword()+" => "+encodePasswordWithBCrypt(user.getPassword()));
        user.setPassword(encodePasswordWithBCrypt(user.getPassword()));
        userService.create(user);
        userService.createUserDetails(new UserDetails(user.getUserName(),user.getName(),"","","","","","",""));
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpSession session, Model model,
            @RequestParam(value = "error", required = false) String error){

        if(session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if(user.getRole().equals("ROLE_ADMIN")){
                return "redirect:/admin/home";
            }
            return "redirect:/user/home";
        }

        System.out.println("login method.............");
        if (error!=null){
            model.addAttribute("error","Incorrect user name or password. Plz try again.");
        }

        return "loginpage";
    }

    /*@RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        System.out.println("loging out...");
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }*/

    @RequestMapping(value = "/login/done")
    public String login(HttpSession session, Principal principal){
        System.out.println("in user controller : "+principal.getName());
        User user = userService.getUser(principal.getName());
        session.setAttribute("user",user);
        if(user.getRole().equals("ROLE_ADMIN")){
            return "redirect:/admin/home";
        }
        return "redirect:/user/home";
    }

    public @ResponseBody User Json(){
        User user = new User("Jackson","json@jackson.com","jsonn","noPass","Anonymus",true);
        user.setId(555);
        System.out.println("json test : "+user);
        return user;
    }

    RestTemplate template = new RestTemplate();

    @RequestMapping(value = "/testFront")
    public String testJson() throws IOException{
        User user = new User("Request Jackson","jackson@json.net","jsonn","password","Anonymus",true);
        user.setId(111);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity<>(user,headers);


        System.out.println("in front end test json..");
        String jsonString = template.exchange("http://localhost:8080/testBack?id={id}",
                HttpMethod.POST,null, String.class, 789).getBody();
        System.out.println("json test : "+jsonString);
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(jsonString,User.class);
        System.out.println("User Object = "+user);
        System.out.println("User Name = "+user.getName());

        return "redirect:/";
    }

}
