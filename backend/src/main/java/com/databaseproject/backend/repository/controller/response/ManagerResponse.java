package com.databaseproject.backend.repository.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManagerResponse {
    private boolean state;

    private String message;
}
