package com.sbrta.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SbrTa
 * @since 8/2/2020  4:54 PM
 */

@RestController
@RefreshScope
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
