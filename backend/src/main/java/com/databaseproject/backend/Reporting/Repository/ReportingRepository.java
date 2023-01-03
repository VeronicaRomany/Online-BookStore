package com.databaseproject.backend.Reporting.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.databaseproject.backend.Reporting.Model.BestSelling;
import com.databaseproject.backend.Reporting.Model.SalesReport;
import com.databaseproject.backend.Reporting.Model.TopCustomer;


@Repository
public class ReportingRepository {
    private final JdbcTemplate jdbcTemplate;

    ReportingRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings(value = "unchecked")
    List<Map<String, Object>> extractResults(Map<String, Object> result){
        return (List<Map<String, Object>>) result.get("#result-set-1");
    }

    private List<SalesReport> mapSalesReport(Map<String, Object> result){
        return extractResults(result).stream().map(x -> new SalesReport(x)).toList();
    }

    private List<TopCustomer> mapTopCustomers(Map<String, Object> result){
        return extractResults(result).stream().map(x -> new TopCustomer(x)).toList();
        
    }

    private List<BestSelling> mapBestSellings(Map<String, Object> result){
        return extractResults(result).stream().map(x -> new BestSelling(x)).toList();
        
    }

    public List<SalesReport> getTotalSales(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return mapSalesReport(jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_sales_report()}");
            return cs;
          }
        }, parameters));

    }

    public List<TopCustomer> getTopCustomers(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return mapTopCustomers(jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_top5_customers()}");
            return cs;
          }
        }, parameters));

    }

    public List<BestSelling> getBestSellings(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return mapBestSellings(jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_top_selling_books}");
            return cs;
          }
        }, parameters));

    }
}
