package com.institute.portal.user_service;

import com.institute.portal.user_service.model.Role;
import com.institute.portal.user_service.model.User;
import com.institute.portal.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User user = new User();
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("password"));
				user.setRole(Role.ADMIN);
				user.setFullName("Admin User");
				userRepository.save(user);
				System.out.println("Sample user created: admin/password");
			}
		};
	}

}
