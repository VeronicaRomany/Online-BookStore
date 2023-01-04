package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.request.*;
import com.databaseproject.backend.response.BookInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.List;

@Repository
public class ManagerRepository implements IManagerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public  getLibraryOrders(ConfirmLibraryOrderRequest request) {
//        int updatedRows = jdbcTemplate.update("CALL confirm_library_order(?)", request.getOrderID());
//
//        return updatedRows != 0;
//    }

    @Override
    public boolean confirmLibraryOrder(ConfirmLibraryOrderRequest request) {
        try {
            jdbcTemplate.update("CALL confirm_library_order(?)", request.getOrderID());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean createLibraryOrder(CreateLibraryOrderRequest request) {
        try {
            jdbcTemplate.update("CALL create_library_order(?, ?)", request.getISBN(), request.getQuantity());
            return true;
        } catch (Exception e) {
            return false;
        }
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
        try {
            jdbcTemplate.update("CALL modify_book(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    request.getOldISBN(), request.getNewISBN(), request.getNewTitle(), request.getNewPublisher(),
                    request.getNewPubYear(), request.getNewPubYear(), request.getNewCategory(), request.getNewStock(),
                    request.getNewThreshold(), request.getNewImageURL());

            for(String author: request.getNewAuthors()){
                jdbcTemplate.update("CALL add_author(?, ?)", request.getNewISBN(), author);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addPublisher(AddPublisherRequest request) {
        try {
            jdbcTemplate.update("CALL add_publisher(?, ?, ?)",
                    request.getName(), request.getName(), request.getAddress());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addBook(AddBookRequest request) {
        try {
            jdbcTemplate.update("CALL add_book(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    request.getISBN(), request.getTitle(), request.getPublisher(), request.getPubYear(),
                    request.getPrice(), request.getCategory(), request.getStock(), request.getThreshold(),
                    request.getImageURL());
            for(String author: request.getAuthors()){
                jdbcTemplate.update("CALL add_author(?, ?)", request.getISBN(), author);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BookInfoResponse findBookByISBN(String ISBN) {
        try {
            List<BookInfoResponse> books = jdbcTemplate.query("CALL get_book_info(?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                List<String> authors = jdbcTemplate.query("CALL get_book_authors(?)",
                        (r, rNum) -> r.getString("Name"), ISBN);
                book.setAuthors(authors);
                return book;
            }, ISBN);

            return books.stream().findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }



}
