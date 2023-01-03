package com.databaseproject.backend.Reporting.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.databaseproject.backend.Reporting.Service.ReportingService;

@RequestMapping("app/manager/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService){
        this.reportingService = reportingService;
    }


    @GetMapping("/sale")
    public ResponseEntity<byte[]> SalesReport(){
        return ResponseEntity.ok().body(this.reportingService.getSalesReport(""));
    }

    @GetMapping("/topCustomers")
    public ResponseEntity<byte[]> topCutomers(){
        return ResponseEntity.ok().body(this.reportingService.getTopCutomers(""));
    }

    @GetMapping("/bestSellings")
    public ResponseEntity<byte[]> bestSellings(){
        return ResponseEntity.ok().body(this.reportingService.getBestSellings(""));
    }

    
}
