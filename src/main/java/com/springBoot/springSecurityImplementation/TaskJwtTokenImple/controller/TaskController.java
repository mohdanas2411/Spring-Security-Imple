package com.springBoot.springSecurityImplementation.TaskJwtTokenImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private User_OneRepo user_oneRepo;

    @GetMapping("/")
    public String welcome() {

        return "Welcome Spring Security";
    }


    @GetMapping("/api/task/get")
    @Cacheable(value = "token")
    public User_One getBook() {

        return user_oneRepo.findByUserName("mohd");
    }


    @PostMapping("api/task/signin")
    public String signIn(@RequestBody User_One user_one) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user_one.getUserName(), user_one.getUserPassword()));
        } catch (Exception e) {
            throw new Exception("user Not found");
        }

        return jwtUtill.generateToken(user_one.getUserName());
    }
}
