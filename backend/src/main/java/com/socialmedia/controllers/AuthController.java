package com.socialmedia.controllers;

import com.socialmedia.exceptions.EmailAlreadyTakenException;
import com.socialmedia.models.AppUser;
import com.socialmedia.models.Registeration;
import com.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken() {
        return new ResponseEntity<String>("The email you provided is already in use", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody Registeration registeration){
        return userService.registerUser(registeration);
    }
}
