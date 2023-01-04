package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLibraryOrderRequest {
    private String ISBN;
    private Integer quantity;
}
