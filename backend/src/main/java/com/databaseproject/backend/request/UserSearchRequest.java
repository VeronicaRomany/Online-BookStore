package com.databaseproject.backend.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {
    private String key;
    private String method;
    private String order;
    private Integer pageNumber;
    private Integer countInPage;
}
