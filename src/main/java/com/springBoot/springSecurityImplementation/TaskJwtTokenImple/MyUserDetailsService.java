package com.springBoot.springSecurityImplementation.TaskJwtToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private User_OneRepo user_oneRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_One userObj = user_oneRepo.findByUserName(username);

        return new org.springframework.security.core.userdetails.User(userObj.getUserName(), userObj.getUserPassword(), new ArrayList<>());
    }
}
