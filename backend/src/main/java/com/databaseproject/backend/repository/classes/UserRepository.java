package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.IUserRepository;
import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.CreditCard;
import com.databaseproject.backend.request.ModifyUserRequest;
import com.databaseproject.backend.request.UserRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository implements IUserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createUser(UserRequest request) {
        try {
            System.out.println(request);
            int updateRows = jdbcTemplate.update("{ CALL create_user (?,?,?,?,?,?,?) }",
                    request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(),
                    request.getEmail(), request.getPhone(), request.getAddress());
            System.out.println(updateRows);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifyUser(ModifyUserRequest request) {
        int updatedRows = jdbcTemplate.update("CALL modify_user(?, ?, ?, ?, ?, ?, ?, ?)",
                request.getOldUserName(), request.getNewUsername(), request.getNewPassword(),
                request.getNewFName(), request.getNewLName(), request.getNewEmail(), request.getNewPhone(),
                request.getNewAddress());

        return updatedRows != 0;
    }

    @Override
    public Integer createUserOrder(CreateOrderRequest request, String username) {
        Integer orderID = jdbcTemplate.queryForObject("CALL create_user_order(?)",
                (rs, rowNum) -> rs.getInt(1), username);

        System.out.println("Order Id = " + orderID);

        Map<String, Integer> ordersMap = request.getOrders();

        for (Map.Entry<String, Integer> entry : ordersMap.entrySet()) {
            jdbcTemplate.update("CALL add_book_to_user_order(?, ?, ?)",
                    orderID, entry.getKey(), entry.getValue());
        }

        CreditCard creditCard = request.getCreditCard();

        try {
            jdbcTemplate.update("CALL verify_user_order_info(?, ?, ?)",
                    orderID, creditCard.getNumber(), Date.valueOf(creditCard.getExpiryDate()));
            return orderID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public UserInfoResponse getUserInfo(String username) {
        List<UserInfoResponse> users = jdbcTemplate.query("CALL get_user_info(?)", (rs, rowNum) -> {
            UserInfoResponse user = new UserInfoResponse();
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setFirstName(rs.getString("FName"));
            user.setLastName(rs.getString("LName"));
            user.setEmail(rs.getString("Email"));
            user.setPhone(rs.getString("Phone"));
            user.setAddress(rs.getString("Address"));
            user.setIsManager(rs.getBoolean("Is_Manager"));
            return user;
        }, username);

        return users.stream().findFirst().orElse(null);
    }

}
