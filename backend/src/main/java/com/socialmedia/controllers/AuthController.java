package com.socialmedia.controllers;

import com.socialmedia.exceptions.EmailAlreadyTakenException;
import com.socialmedia.exceptions.EmailFailedToSendException;
import com.socialmedia.exceptions.IncorrectVerificationCodeException;
import com.socialmedia.exceptions.UserDoesNotExistException;
import com.socialmedia.models.AppUser;
import com.socialmedia.models.Registration;
import com.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

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
    public AppUser register(@RequestBody Registration registration){
        return userService.registerUser(registration);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist() {
        return new ResponseEntity<String>("The user you are looking for does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/phone")
    public AppUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body) {
        String username = body.get("username");
        String phone = body.get("phone");

        AppUser appUser = userService.getUserByUsername(username);
        appUser.setPhoneNumer(phone);
        return userService.updateUser(appUser);
    }



    @ExceptionHandler({EmailFailedToSendException.class})
    public ResponseEntity<String> handleFailedEmail() {
        return new ResponseEntity<String>("Email failed to send, try again in a moment",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body) {
        userService.generateEmailVerification(body.get("username"));
        return new ResponseEntity<String>("Verification code generated, email sent",HttpStatus.OK );
    }

    @ExceptionHandler({IncorrectVerificationCodeException.class})
    public ResponseEntity<String> incorrectCodeHandler() {
        return new ResponseEntity<String>("The Code provided does not match the users code", HttpStatus.CONFLICT);
    }

    @PostMapping("/email/verify")
    public AppUser verifyEmail(@RequestBody LinkedHashMap<String, String> body) {
        Long code = Long.parseLong(body.get("code"));
        String username = body.get("username");

        return userService.verifyEmail(username, code);
    }

    @PutMapping("/update/password")
    public AppUser updatePassword(@RequestBody LinkedHashMap<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        return userService.setPassword(username, password);
    }
}
