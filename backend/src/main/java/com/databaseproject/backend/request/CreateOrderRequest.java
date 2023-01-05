package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CreateOrderRequest {
    private CreditCard creditCard;
    private Map<String , Integer> orders;
}
