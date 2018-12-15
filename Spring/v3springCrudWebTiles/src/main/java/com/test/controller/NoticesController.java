package com.test.controller;

import com.test.dao.Notice;
import com.test.service.NoticesService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class NoticesController {

    private NoticesService noticesService;

    @Autowired
    public void setNoticesService(NoticesService noticesService){
        this.noticesService=noticesService;
    }


    @RequestMapping(value = "/notices", method = RequestMethod.GET)
    public String showNotices(Model model, HttpSession session){
        List<Notice> notices = noticesService.getCurrent();
        model.addAttribute("title", "Test Project");
        model.addAttribute("notices",notices);
        return "notices";
    }

    @RequestMapping(value = "/createnotice")
    public String createNotice(Model model){
        model.addAttribute(new Notice());
        return "createnotice";
    }

    @RequestMapping(value = "doCreate", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid Notice notice, BindingResult result){

        if (result.hasErrors()){
            return "createnotice";
        }

        noticesService.create(notice);

        return "noticeCreated";
    }




}
