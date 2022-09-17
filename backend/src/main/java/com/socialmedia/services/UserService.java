package com.socialmedia.services;


import com.socialmedia.exceptions.EmailAlreadyTakenException;
import com.socialmedia.exceptions.UserDoesNotExistException;
import com.socialmedia.models.AppUser;
import com.socialmedia.models.Registration;
import com.socialmedia.models.Role;
import com.socialmedia.repositories.RoleRepository;
import com.socialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public AppUser registerUser(Registration registration){
        AppUser appUser = new AppUser();
        appUser.setFirstName(registration.getFirstName());
        appUser.setLastName(registration.getLastName());
        appUser.setEmail(registration.getEmail());
        appUser.setDateOfBirth(registration.getDob());

        String name = appUser.getFirstName() + appUser.getLastName();
        boolean nameTaken = true;
        String tempName = "";
        while (nameTaken) {
            tempName = generateUsername(name);
            if (userRepository.findByUsername(tempName).isEmpty()) {
                nameTaken = false;
            }
        }

        appUser.setUsername(tempName);

        Set<Role> roles = appUser.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        appUser.setAuthorities(roles);

        try {
            return  userRepository.save(appUser);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }

    }

    public AppUser updateUser(AppUser appUser) {
        try{
            return userRepository.save(appUser);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }
    }

    private String generateUsername(String name){
        long generatedNumber = (long) Math.floor(Math.random()* 1_000_000_000);
        return name+generatedNumber;
    }

    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public void generateEmailVerification(String username) {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        appUser.setVerification(generateVerificationCode());

        userRepository.save(appUser);
    }

    private Long generateVerificationCode() {
        return (long) Math.floor(Math.random()* 1_000_000_000);
    }
}
