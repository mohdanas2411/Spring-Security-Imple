package com.springBoot.springSecurityImplementation.TaskJwtToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User_OneRepo extends JpaRepository<User_One, String> {

    public User_One findByUserName(String username);
}
