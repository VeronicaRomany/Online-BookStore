package com.databaseproject.backend.Reporting.Service;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.databaseproject.backend.Reporting.Repository.ReportingRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportingService {

        private final ReportingRepository repo;
        private String basePath = "";

        public ReportingService(ReportingRepository reportingRepository){
            this.repo = reportingRepository;
        }

        protected byte[] generateReport(String templatePath, Supplier<List<?>> supplier){
            byte[] rv = new byte[]{};
            // String html = "", htmlPath = basePath + "backend\\src\\main\\resources\\static\\Reports\\temp.pdf";
            // List<TopCustomer> topCustomers = this.repo.getTopCustomers();
            try {
                File file = ResourceUtils.getFile("classpath:\\static\\Reports\\" + templatePath);
                JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supplier.get());
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
                rv = JasperExportManager.exportReportToPdf(jasperPrint);

            } catch (JRException | IOException e) {
                e.printStackTrace();
            }
            return rv;
        }

        public byte[] getTopCutomers(String basePath){
            this.basePath = basePath;
            return generateReport("Top 5 Customers.jrxml", this.repo::getTopCustomers);
        }

        public byte[] getBestSellings(String basePath){
            this.basePath = basePath;

            return generateReport("BestSellings.jrxml", this.repo::getBestSellings);
        }

        public byte[] getSalesReport(String basePath){
            this.basePath = basePath;
            return generateReport("Total sales.jrxml", this.repo::getTotalSales);
        }

    
}
