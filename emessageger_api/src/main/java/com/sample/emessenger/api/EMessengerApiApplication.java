package com.sample.emessenger.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.sample.emessenger.api.entity.Role;
import com.sample.emessenger.api.entity.util.ERole;
import com.sample.emessenger.api.repository.RoleRepository;
import com.sample.emessenger.api.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class EMessengerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EMessengerApiApplication.class, args);
	}

	@Bean
	InitializingBean loadDatabaseData(UserRepository userRepository, RoleRepository roleRepository) {
		return () -> {
			//userRepository.save(new User(null, "ruchira", "ruchira@gmail.com", "ruchira", "089787878", "ddfd", null));
			//userRepository.save(new User(null, "bandara", "bandara@gmail.com", "bandara", "089787879", "ddfds", null));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_SUPERUSER));
			roleRepository.save(new Role(ERole.ROLE_USER));
		};
	}

}
