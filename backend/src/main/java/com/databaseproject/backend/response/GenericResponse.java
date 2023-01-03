package com.databaseproject.backend.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericResponse {
    private boolean state;
    private String message;
    private Object body;
}
