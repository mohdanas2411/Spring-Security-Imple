package com.springBoot.springSecurityImplementation;

import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.repository.User_OneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@CacheConfig(cacheNames = "token")
public class SpringSecurityImplementationApplication {

	@Autowired
	User_OneRepo user_oneRepo;
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityImplementationApplication.class, args);
	}

}
