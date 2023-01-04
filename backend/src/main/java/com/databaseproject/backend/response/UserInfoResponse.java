package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    Boolean isManager;
}
