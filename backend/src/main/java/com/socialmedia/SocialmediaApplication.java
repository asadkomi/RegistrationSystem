package com.socialmedia;

import com.socialmedia.models.AppUser;
import com.socialmedia.models.Role;
import com.socialmedia.repositories.RoleRepository;
import com.socialmedia.repositories.UserRepository;
import com.socialmedia.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialmediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialmediaApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserService userService) {
        return args -> {
            roleRepository.save(new Role(1, "USER"));
            AppUser appUser = new AppUser();
            appUser.setFirstName("Asad");
            appUser.setLastName("Komi");

            userService.registerUser(appUser);
        };
    }
}
