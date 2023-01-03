package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.IUserRepository;
import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.CreditCard;
import com.databaseproject.backend.request.ModifyUserRequest;
import com.databaseproject.backend.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        int updateRows = jdbcTemplate.update("CALL create_user(?, ?, ?, ?, ?, ?, ?)",
                request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPhone(), request.getAddress());

        return updateRows != 0;
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
    public boolean createUserOrder(CreateOrderRequest request, String username) {
        Integer orderID = jdbcTemplate.queryForObject("CALL create_user_order(?)",
                (rs, rowNum) -> rs.getInt(1), username);

        Map<String, Integer> ordersMap = request.getOrders();

        for (Map.Entry<String, Integer> entry : ordersMap.entrySet()) {
            jdbcTemplate.update("CALL add_book_to_user_order(?, ?, ?)",
                    orderID, entry.getKey(), entry.getValue());
        }

        CreditCard creditCard = request.getCreditCard();

        try {
            jdbcTemplate.update("CALL verify_user_order_info(?, ?, ?, ?)",
                    orderID, creditCard.getNumber(), creditCard.getCvc(), creditCard.getExpiryDate());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
