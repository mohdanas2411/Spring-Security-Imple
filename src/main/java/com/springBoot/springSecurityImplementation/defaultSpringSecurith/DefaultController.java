package com.springBoot.springSecurityImplementation.defaultSpringSecurith;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/api/security/default")
    public String defaultSpringSecurity(){
        return "home";
    }
}
