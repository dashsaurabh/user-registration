package org.domain.user.registration;

import org.domain.user.registration.domain.commands.UserCreateDTO;
import org.domain.user.registration.services.commands.UserCommandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class RegistrationApplication {

    public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			UserCommandService userCommandService
	) {
		return (evt) -> Arrays.asList(
				"user,admin,john,robert,ana".split(",")).forEach(
				username -> {
					UserCreateDTO userCreateDTO = new UserCreateDTO();
					userCreateDTO.setUserName(username);
					userCreateDTO.setPassword("password123");
					userCreateDTO.setFirstName(username);
					userCreateDTO.setLastName("LastName");
					userCommandService.createUser(userCreateDTO);

				}
		);}
}

