package com.databaseproject.backend.Reporting.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.util.ResourceUtils;

import com.databaseproject.backend.Reporting.Repository.ReportingRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportingService {

        private final ReportingRepository repo;
        private String basePath = "";

        public ReportingService(ReportingRepository reportingRepository){
            this.repo = reportingRepository;
        }

        protected String generateReport(String templatePath, Supplier<List<?>> supplier){
            String html = "", htmlPath = basePath + "backend\\src\\main\\resources\\static\\Reports\\temp.html";
            // List<TopCustomer> topCustomers = this.repo.getTopCustomers();
            try {
                File file = ResourceUtils.getFile("classpath:\\static\\Reports\\" + templatePath);
                JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supplier.get());
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
                JasperExportManager.exportReportToHtmlFile(jasperPrint, htmlPath);

                StringBuilder htmlBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(htmlPath))));
                String line = null;
                while((line = br.readLine()) != null){
                    htmlBuilder.append(line);
                }
                html = htmlBuilder.toString();

            } catch (JRException | IOException e) {
                e.printStackTrace();
            }
            return html;
        }

        public String getTopCutomers(String basePath){
            this.basePath = basePath;
            return generateReport("Top 5 Customers.jrxml", this.repo::getTopCustomers);
        }

        public String getBestSellings(String basePath){
            this.basePath = basePath;

            return generateReport("BestSellings.jrxml", this.repo::getBestSellings);
        }

        public String getSalesReport(String basePath){
            this.basePath = basePath;
            return generateReport("Total sales.jrxml", this.repo::getTotalSales);
        }

    
}
