package com.databaseproject.backend.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.databaseproject.backend.Utils.TokenUtil;
import com.databaseproject.backend.model.UserAuthentication;
import com.databaseproject.backend.response.SignInResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserFinder implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;
    private final TokenUtil tokenUtil;

    public UserFinder(JdbcTemplate jdbcTemplate,@Lazy TokenUtil tokenUtil){
        this.jdbcTemplate = jdbcTemplate;
        this.tokenUtil = tokenUtil;
    }


    public SignInResponse userLogIn(Authentication auth) {
        try {
            UserDetails user = this.loadUserByUsername(auth.getName());
            String token = tokenUtil.generateToken(auth);
            return new SignInResponse(token, !user.getAuthorities().isEmpty());
        } catch (Exception e) {
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    protected UserAuthentication mapUser(Map<String, Object> queryResult){
        Map<String, Object> map =  (Map<String, Object>) ((List<Map<String, Object>>)queryResult.get("#result-set-1")).get(0);
        // Map<String, Object> map = Map.of();
        System.out.println(map);
        log.info(map.toString());
        return new UserAuthentication(map);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        System.out.println(username);
        
        UserAuthentication user =  mapUser(jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call find_by_username(?)}");
            cs.setString(1, username);
            return cs;
          }
        }, parameters));

        System.out.println(user.getPassword());
        return user;
    }
    
}
