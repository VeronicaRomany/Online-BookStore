package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPublisherRequest {
    private String name;
    private String address;
    private String phone;
}
