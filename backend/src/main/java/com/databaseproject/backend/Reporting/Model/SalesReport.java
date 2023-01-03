package com.databaseproject.backend.Reporting.Model;

import java.util.Map;

public class SalesReport {
    private final int day, month;
    private final long count;
    private final double total;


    public SalesReport(Map<String, Object> record){
        this.day = (int) record.get("day");
        this.month = (int) record.get("month");
        this.count = (long) record.get("count");
        this.total = (double) record.get("total");
    }
}
