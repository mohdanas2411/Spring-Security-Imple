package com.springBoot.springSecurityImplementation.TaskJwtTokenImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
        ConcurrentHashMap<String,User_One> cache = (ConcurrentHashMap<String, User_One>) cacheManager.getCache("token").getNativeCache();
        if (cache.get(username) != null){
             userObj =  cache.get(username);
        }else {
            userObj = user_oneRepo.findByUserName(username);
            cache.put(username,userObj);
        }


       // springCacheBasedUserCache.f


        return new org.springframework.security.core.userdetails.User(userObj.getUserName(), userObj.getUserPassword(), new ArrayList<>());
    }
}
