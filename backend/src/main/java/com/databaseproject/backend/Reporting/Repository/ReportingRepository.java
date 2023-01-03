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


@Repository
public class ReportingRepository {
    private final JdbcTemplate jdbcTemplate;

    ReportingRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getTotalSales(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_sales_report()}");
            return cs;
          }
        }, parameters);

    }

    public Map<String, Object> getTopCustomers(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_top5_customers()}");
            return cs;
          }
        }, parameters);

    }

    public Map<String, Object> getBestSellings(){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call get_top_selling_books}");
            return cs;
          }
        }, parameters);

    }
}
