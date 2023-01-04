package com.databaseproject.backend.Reporting.Model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SalesReport {
    private final String date;
    private final long count;
    private final double total;


    public SalesReport(Map<String, Object> record){
        this.date = (int) record.get("day") + " / " + (int) record.get("month");
        this.count = (long) record.get("count");
        this.total = (double) record.get("total");
    }
}
