package com.databaseproject.backend.controller;

import com.databaseproject.backend.repository.interfaces.IUserRepository;
import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.ModifyUserRequest;
import com.databaseproject.backend.request.UserRequest;
import com.databaseproject.backend.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class UserController {
    private final IUserRepository userRepository;

    @Autowired
    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<GenericResponse> signUpUser(@RequestBody UserRequest user) {
        boolean isCreated = userRepository.createUser(user);

        if (isCreated)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Account created successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to create account!", null));
    }

    @PatchMapping("/user")
    public ResponseEntity<GenericResponse> modifyUser(@RequestBody ModifyUserRequest modifiedUser,
                                                      Authentication auth) {
        boolean isModified = userRepository.modifyUser(modifiedUser);

        if (isModified)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Account modified successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to modify account!", null));
    }

    @PostMapping("/order")
    public ResponseEntity<GenericResponse> placeOrder(@RequestBody CreateOrderRequest order,
                                                      Authentication auth) {
        Integer orderID = userRepository.createUserOrder(order, auth.getName());

        if (orderID > 0)
            return ResponseEntity
                    .ok(new GenericResponse(true, "Order created successfully!", null));

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(false, "Failed to create your oder!", orderID));
    }

}