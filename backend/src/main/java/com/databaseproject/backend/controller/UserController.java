package com.databaseproject.backend.controller;

import com.databaseproject.backend.config.SecurityConfiguration.EncoderService;
import com.databaseproject.backend.repository.interfaces.IUserRepository;
import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.ModifyUserRequest;
import com.databaseproject.backend.request.UserRequest;
import com.databaseproject.backend.response.GenericResponse;
import com.databaseproject.backend.response.SignInResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import com.databaseproject.backend.service.UserFinder;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class UserController {



    private final EncoderService encoderService;
    private final IUserRepository userRepository;
    private final UserFinder userFinder;

    private final String test = "/test";

    @Autowired
    public UserController(IUserRepository userRepository, UserFinder userFinder, EncoderService encoderService) {
        this.userRepository = userRepository;
        this.userFinder = userFinder;
        this.encoderService = encoderService;
    }

    @PostMapping(test + "/user")
    public ResponseEntity<GenericResponse> signUpUser(@RequestBody UserRequest user) {
        System.out.println(user);
        user.setPassword(encoderService.encode(user.getPassword()));
        boolean isCreated = userRepository.createUser(user);

        if (isCreated)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Account created successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to create account!", null));
    }

    @PostMapping(test + "/user/auth")
    public ResponseEntity<SignInResponse> signInUser(Authentication credentials) {

        SignInResponse response = userFinder.userLogIn(credentials);
        if (response == null) {
            return ResponseEntity.status(422).body(null);
        }

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(test + "/user")
    public ResponseEntity<GenericResponse> modifyUser(@RequestBody ModifyUserRequest modifiedUser,
                                                      Authentication auth) {
        boolean isModified = userRepository.modifyUser(modifiedUser);

        if (isModified)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Account modified successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to modify account!", null));
    }

    @PostMapping(test + "/order")
    public ResponseEntity<GenericResponse> placeOrder(@RequestBody CreateOrderRequest order,
                                                      Authentication auth) {
        Integer orderID = userRepository.createUserOrder(order, auth.getName());

        if (orderID > 0)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Order created successfully!", orderID));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to create your oder!", null));
    }

    @GetMapping(test + "/user")
    public ResponseEntity<UserInfoResponse> getUserInfo(Authentication auth) {
        UserInfoResponse user = userRepository.getUserInfo(auth.getName());

        if(user != null)
            return ResponseEntity.ok(user);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}