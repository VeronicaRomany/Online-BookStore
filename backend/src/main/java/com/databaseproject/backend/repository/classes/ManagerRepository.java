package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;

@Repository
public class ManagerRepository implements IManagerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean confirmLibraryOrder(ConfirmLibraryOrderRequest request) {
        int updatedRows = jdbcTemplate.update("CALL confirm_library_order(?)", request.getOrderID());

        return updatedRows != 0;
    }

    @Override
    public boolean createLibraryOrder(CreateLibraryOrderRequest request) {
        int updatedRows = jdbcTemplate.update("CALL create_library_order(?, ?)",
                request.getISBN(), request.getQuantity());

        return updatedRows != 0;
    }

    @Override
    public boolean promoteUser(PromoteUserRequest request, String managerUsername) {
        try {
            jdbcTemplate.execute("CALL promote_user(?, ?)", (CallableStatement cs) -> {
                cs.setString(1, managerUsername);
                cs.setString(2, request.getUserUsername());
                return cs.execute();
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modifyBook(ModifyBookRequest request) {
        int updatedRows = jdbcTemplate.update("CALL modify_book(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                request.getOldISBN(), request.getNewISBN(), request.getNewTitle(), request.getNewPublisher(),
                request.getNewPubYear(), request.getNewPubYear(), request.getNewCategory(), request.getNewStock(),
                request.getNewThreshold());

        return updatedRows != 0;
    }

    @Override
    public boolean addPublisher(AddPublisherRequest request) {
        int updatedRows = jdbcTemplate.update("CALL add_publisher(?, ?, ?)",
                request.getName(), request.getName(), request.getAddress());

        return updatedRows != 0;
    }

    @Override
    public boolean addBook(AddBookRequest request) {
        int updatedRows = jdbcTemplate.update("CALL add_book(?, ?, ?, ?, ?, ?, ?, ?)",
                request.getISBN(), request.getTitle(), request.getPublisher(), request.getPubYear(),
                request.getPrice(), request.getCategory(), request.getStock(), request.getThreshold());

        return updatedRows != 0;
    }

}
