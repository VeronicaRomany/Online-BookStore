package com.databaseproject.backend.Reporting.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.databaseproject.backend.Reporting.Model.BestSelling;
import com.databaseproject.backend.Reporting.Model.SalesReport;
import com.databaseproject.backend.Reporting.Model.TopCustomer;
import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import net.sf.jasperreports.engine.export.data.DateTextValue;


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
        List<SalesReport> sales =  extractResults(result).stream().map(x -> new SalesReport(x)).toList();
        List<SalesReport> res = new LinkedList<>();
        LocalDate end = LocalDate.now(), start = end.minusMonths(1);
        ListIterator<SalesReport> iter = sales.listIterator();

        while(iter.hasNext()){
          SalesReport report = iter.next();
          String[] date = report.getDate().split(" / ");
          if(Integer.parseInt(date[0]) == start.getDayOfMonth() && Integer.parseInt(date[1]) == start.getMonthValue()){
            res.add(report);
          }else{
            res.add(new SalesReport(start.getDayOfMonth() + " / " + start.getMonthValue(), 0, 0));
            iter.previous();
          }
          start = start.plusDays(1);
        }
        while(start.isBefore(end)){
            res.add(new SalesReport(start.getDayOfMonth() + " / " + start.getMonthValue(), 0, 0));
            start = start.plusDays(1);

        }
        return res;
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
            CallableStatement cs = con.prepareCall("{call get_top_selling_books()}");
            return cs;
          }
        }, parameters));

    }
}
