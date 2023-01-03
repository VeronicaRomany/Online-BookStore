package com.databaseproject.backend.Reporting.Model;

import java.util.Map;

public class BestSelling {
    private final String isbn, title;
    private final int amount;

    public BestSelling(Map<String, Object> record){
        this.isbn = (String) record.get("isbn");
        this.title = (String) record.get("title");
        this.amount = (int) record.get("amount");
    }

}
