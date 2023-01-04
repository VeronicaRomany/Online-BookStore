package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyUserRequest {
    private String oldUserName;
    private String newUsername;
    private String newPassword;
    private String newFName;
    private String newLName;
    private String newEmail;
    private String newPhone;
    private String newAddress;
}
