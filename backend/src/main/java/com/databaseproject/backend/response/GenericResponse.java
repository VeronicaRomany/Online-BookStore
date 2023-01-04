package com.databaseproject.backend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericResponse {
    private boolean state;
    private String message;
    private Object body;
}
