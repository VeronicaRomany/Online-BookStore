package com.databaseproject.backend.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class CreditCard implements Serializable {
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
}
