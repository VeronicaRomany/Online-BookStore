package com.databaseproject.backend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
