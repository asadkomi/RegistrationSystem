package com.socialmedia.services;


import com.socialmedia.models.AppUser;
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

    public AppUser registerUser(AppUser appUser){
        Set<Role> roles = appUser.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        appUser.setAuthorities(roles);

        return userRepository.save(appUser);
    }
}
