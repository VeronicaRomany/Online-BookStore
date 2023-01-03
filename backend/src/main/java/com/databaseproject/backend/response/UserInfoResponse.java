package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    String username;
    String firstName;
    String lastName;
    String email;
    String phone;
}
