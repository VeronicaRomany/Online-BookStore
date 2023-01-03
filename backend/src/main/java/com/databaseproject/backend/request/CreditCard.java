package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CreditCard implements Serializable {
    private String number;
    private String cvc;
    private LocalDate expiryDate;
}
