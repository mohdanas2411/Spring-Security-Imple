package com.springBoot.springSecurityImplementation.TaskJwtTokenImple.controller;

import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.entity.Role;
import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.utills.JwtUtill;
import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.entity.User_One;
import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.repository.User_OneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

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


    @Secured("ROLE_ADMIN")
    @GetMapping("/api/task/admin")
    public String admin(){
        return "admin IN";
    }



    @GetMapping("/api/task/get")
    // @Cacheable(value = "token")
    public User_One getBook() {

//
//        User_One user_one1 = new User_One();
//
//        user_one1.setUserName("xyz");
//        user_one1.setUserPassword("$2a$12$8Q7GCsvD59SdIHMDt87P3.tjUhA618XPhLq.H9VrAmig4DG63JcnO");
//        user_one1.setAddress("delhi");
//        user_one1.setPhoneNo(987654321);
//        user_one1.setRole(Role.ROLE_USER);
//
//        user_oneRepo.save(user_one1);



        System.out.println("authoritites :    ");
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(System.out::println);

        return user_oneRepo.findByUserName("Abc");
    }


    @PostMapping("api/task/signin")
    public String signIn(@RequestBody User_One user_one) throws Exception {


//        User_One obj = new User_One();
//
//
//        obj.setUserName("Abc");
//        obj.setUserPassword("$2a$12$ugOlHymRtPPM8Arq.icB7Ot7XMoPYIhMMHI7FycRsU0s4O8foEDPW");
//        obj.setAddress("shaheenbagh");
//        obj.setPhoneNo(981811172);
//        obj.setRole(Role.ROLE_ADMIN);
//
//        user_oneRepo.save(obj);

//
//
//
//

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user_one.getUserName(), user_one.getUserPassword()));
        } catch (Exception e) {
            throw new Exception("user Not found");
        }

        return jwtUtill.generateToken(user_one.getUserName());
    }



    @GetMapping("/accessdenied")
    public String notAuthorize(){
        return "you are not authorize for this resouce";
    }

}
