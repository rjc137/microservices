package com.sample.authorization.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.authorization.api.entity.Role;
import com.sample.authorization.api.entity.Roles;
import com.sample.authorization.api.repository.RoleRepository;
import com.sample.authorization.api.repository.UserRepository;


@SpringBootApplication
public class IdentityAccessManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityAccessManagementServiceApplication.class, args);
    }

    @Bean
    InitializingBean loadDatabaseData(UserRepository userRepository, RoleRepository roleRepository) {
        return () -> {
            // userRepository.save(new User(null, "ruchira", "ruchira@gmail.com", "ruchira", "089787878", "ddfd", null));
            // userRepository.save(new User(null, "bandara", "bandara@gmail.com", "bandara", "089787879", "ddfds", null));
            roleRepository.save(new Role(null, Roles.ROLE_USER, null, null, null, null));
            roleRepository.save(new Role(null, Roles.ROLE_ADMIN, null, null, null, null));
            roleRepository.save(new Role(null, Roles.ROLE_CUSTOMER, null, null, null, null));
            roleRepository.save(new Role(null, Roles.ROLE_SUPERUSER, null, null, null, null));
        };
    }
}
