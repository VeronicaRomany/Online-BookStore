package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateOrderRequest {
    private CreditCard creditCard;
    private Map<String , Integer> orders;
}
