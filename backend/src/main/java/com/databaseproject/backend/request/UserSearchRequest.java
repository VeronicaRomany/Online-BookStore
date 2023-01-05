package com.databaseproject.backend.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {
    private String key;
    private String method;
    private String order;//A
    private Integer pageNumber;//1
    private Integer countInPage;//10000
}
