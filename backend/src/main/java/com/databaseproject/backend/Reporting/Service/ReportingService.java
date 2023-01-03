package com.databaseproject.backend.Reporting.Service;

import java.util.List;
import java.util.Map;

import com.databaseproject.backend.Reporting.Model.BestSelling;
import com.databaseproject.backend.Reporting.Model.SalesReport;
import com.databaseproject.backend.Reporting.Model.TopCustomer;
import com.databaseproject.backend.Reporting.Repository.ReportingRepository;

public class ReportingService {

        private final ReportingRepository repo;

        public ReportingService(ReportingRepository reportingRepository){
            this.repo = reportingRepository;
        }

        @SuppressWarnings(value = "unchecked")
        List<Map<String, Object>> extractResults(Map<String, Object> result){
            return (List<Map<String, Object>>) result.get("#result-set-1");
        }
        

        private List<SalesReport> mapSalesReports(Map<String, Object> result){
            return extractResults(result).stream().map(x -> new SalesReport(x)).toList();
        }

        private List<TopCustomer> mapTopCustomers(Map<String, Object> result){
            return extractResults(result).stream().map(x -> new TopCustomer(x)).toList();
            
        }

        private List<BestSelling> mapBestSellings(Map<String, Object> result){
            return extractResults(result).stream().map(x -> new BestSelling(x)).toList();
            
        }

        String getTopCutomers(){
            
            return "";
        }

    
}
