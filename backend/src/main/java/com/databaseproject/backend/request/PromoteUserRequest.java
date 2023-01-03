package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoteUserRequest {
//    private String managerUserName; // accessible from authentication token
    private String userUsername;
}
