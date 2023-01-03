package com.databaseproject.backend.Reporting.Service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.databaseproject.backend.Reporting.Model.BestSelling;
import com.databaseproject.backend.Reporting.Model.SalesReport;
import com.databaseproject.backend.Reporting.Model.TopCustomer;
import com.databaseproject.backend.Reporting.Repository.ReportingRepository;

public class ReportingServiceTest {

    ReportingRepository repo;

    ReportingService service;

    @BeforeEach
    void init(){
        repo = Mockito.mock(ReportingRepository.class);
        service = new ReportingService(repo);
    }

    @Test
    void testGetBestSellings() {
        List<BestSelling> data = List.of(
            new BestSelling("122345", "title1", 5),
            new BestSelling("1223456", "title2", 6),
            new BestSelling("122347", "title3", 7),
            new BestSelling("122348", "title4", 8),
            new BestSelling("122349", "title5", 9),
            new BestSelling("122350", "title6", 10)
        );

        when(repo.getBestSellings()).thenReturn(data);

        assertDoesNotThrow(() -> service.getBestSellings("D:\\CSED2024\\Fall 2022\\DBMS\\Online-BookStore\\"));

    }

    @Test
    void testGetSalesReport() {
        List<SalesReport> data = List.of(
            new SalesReport("10 / 12", 3, 75),
            new SalesReport("11 / 12", 4, 150),
            new SalesReport("12 / 12", 5, 80),
            new SalesReport("13 / 12", 6, 100),
            new SalesReport("14 / 12", 8, 120),
            new SalesReport("15 / 12", 20, 220),
            new SalesReport("16 / 12", 13, 140),
            new SalesReport("17 / 12", 1, 20),
            new SalesReport("18 / 12", 5, 100),
            new SalesReport("19 / 12", 8, 150),
            new SalesReport("20 / 12", 12, 200),
            new SalesReport("21 / 12", 4, 80)
        );
        when(repo.getTotalSales()).thenReturn(data);
        assertDoesNotThrow(() -> service.getSalesReport("D:\\CSED2024\\Fall 2022\\DBMS\\Online-BookStore\\"));
    }

    @Test
    void testGetTopCutomers() {
        List<TopCustomer> data = List.of(
            new TopCustomer("One", 100),
            new TopCustomer("Two", 200),
            new TopCustomer("Three", 350),
            new TopCustomer("Four", 100.5),
            new TopCustomer("Five", 50),
            new TopCustomer("Six", 60),
            new TopCustomer("Seven", 65)

        );

        when(repo.getTopCustomers()).thenReturn(data);
        String html = assertDoesNotThrow(() -> service.getTopCutomers("D:\\CSED2024\\Fall 2022\\DBMS\\Online-BookStore\\"));

    }
}
