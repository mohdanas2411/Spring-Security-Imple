package com.springBoot.springSecurityImplementation.TaskJwtTokenImple.services;

import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.entity.User_One;
import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.repository.User_OneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private User_OneRepo user_oneRepo;


    @Autowired
    private CacheManager cacheManager;


    @Override
    // @Cacheable(value = "token",key = "username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //  User_One userObj = user_oneRepo.findByUserName(username);


        User_One userObj;


        ConcurrentHashMap<String, User_One> cache = (ConcurrentHashMap<String, User_One>) cacheManager.getCache("token").getNativeCache();
        if (cache.get(username) != null) {
            userObj = cache.get(username);
        } else {
            userObj = user_oneRepo.findByUserName(username);
            cache.put(username, userObj);
        }

        if (userObj != null) {

            System.out.println("FOUND");
            // return userObj.toCurrentUserDetails();
            return userObj;
        }

        throw new UsernameNotFoundException("User "+username + " is not found");

        // springCacheBasedUserCache.f

//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = asList(new Prin(userObj.getRole()));
//        return new org.springframework.security.core.userdetails.User(userObj.getUserName(), userObj.getUserPassword(), simpleGrantedAuthorities);


    }
}



