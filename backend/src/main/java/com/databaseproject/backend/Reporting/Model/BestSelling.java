package com.databaseproject.backend.Reporting.Model;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BestSelling {
    private final String isbn, title;
    private final BigDecimal amount;

    public BestSelling(Map<String, Object> record){
        this.isbn = (String) record.get("isbn");
        this.title = (String) record.get("title");
        this.amount = (BigDecimal) record.get("amount");
    }

}
