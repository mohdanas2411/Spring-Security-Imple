package com.springBoot.springSecurityImplementation.TaskJwtTokenImple.repository;

import com.springBoot.springSecurityImplementation.TaskJwtTokenImple.entity.User_One;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_OneRepo extends JpaRepository<User_One, String> {

    public User_One findByUserName(String username);
}
