package com.databaseproject.backend.repository.controller.api;

import com.databaseproject.backend.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserRepository userService;

}
