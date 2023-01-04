package com.databaseproject.backend.Reporting.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.databaseproject.backend.Reporting.Service.ReportingService;

@RequestMapping("api/v1/manager/reports")
@Controller
@CrossOrigin
public class ReportingController {
    private  HttpHeaders headers;

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService){
        this.reportingService = reportingService;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
    }


    @GetMapping("/sale")
    public ResponseEntity<byte[]> SalesReport(){
        return new ResponseEntity<byte[]>(this.reportingService.getSalesReport(""), headers, HttpStatus.OK);
    }

    @GetMapping("/topCustomers")
    public ResponseEntity<byte[]> topCutomers(){
        return new ResponseEntity<byte[]>(this.reportingService.getTopCutomers(""), headers, HttpStatus.OK);

    }

    @GetMapping("/bestSellings")
    public ResponseEntity<byte[]> bestSellings(){
        return new ResponseEntity<byte[]>(this.reportingService.getBestSellings(""), headers, HttpStatus.OK);

    }

    
}
