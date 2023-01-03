package com.databaseproject.backend.Reporting.Model;

import java.util.Map;

public class TopCustomer {
    private final String username;
    private final double amount;

    public TopCustomer(Map<String, Object> record){
        this.username = (String) record.get("username");
        this.amount = (double) record.get("amount");
    }
}
