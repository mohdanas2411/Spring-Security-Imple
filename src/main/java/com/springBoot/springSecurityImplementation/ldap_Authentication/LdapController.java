package com.springBoot.springSecurityImplementation.ldap_Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {

    @GetMapping("/api/home")
    public String ldap(){
        return "welcome ldap auth";
    }
}
