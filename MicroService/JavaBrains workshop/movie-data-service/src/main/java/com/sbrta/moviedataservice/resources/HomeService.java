package com.sbrta.moviedataservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SbrTa
 * @since 8/5/2020  1:14 AM
 */

@RestController
public class HomeService {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.desc: Default Desc}")
    private String appDesc;

    @RequestMapping("/")
    public String getCatalog() {
        return appName + " (" + appDesc + ") is running";
    }
}
