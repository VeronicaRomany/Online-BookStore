package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.ISearchingRepository;
import com.databaseproject.backend.request.SearchByRequest;
import com.databaseproject.backend.request.UserSearchRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import com.databaseproject.backend.request.DetailedBookSearchRequest;
import com.databaseproject.backend.searchkey.IntKey;
import com.databaseproject.backend.searchkey.StringKey;
import com.databaseproject.backend.searchkey.YearKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchingRepository implements ISearchingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserInfoResponse> searchUsers(UserSearchRequest request) {
        try {
            return jdbcTemplate.query("CALL search_user(?, ?, ?, ?)", (rs, rowNum) -> {
                UserInfoResponse user = new UserInfoResponse();
                user.setUsername(rs.getString("Username"));
                user.setFirstName(rs.getString("FName"));
                user.setLastName(rs.getString("LName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setIsManager(rs.getBoolean("Is_Manager"));
                return user;
            }, request.getKey(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBooks(DetailedBookSearchRequest request) {
        try {
            return jdbcTemplate.query("CALL search_book(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (rs, rowNum) -> {
                        BookInfoResponse book = new BookInfoResponse();
                        book.setISBN(rs.getString("ISBN"));
                        book.setTitle(rs.getString("Title"));
                        book.setPublisher(rs.getString("Publisher"));
                        book.setPubYear(rs.getInt("Pub_Year"));
                        book.setCategory(rs.getString("Category"));
                        book.setStock(rs.getInt("Stock"));
                        book.setThreshold(rs.getInt("Threshold"));
                        book.setImageURL(rs.getString("Image_URL"));
                        return book;
                    }, request.getISBN(), request.getTitle(), request.getPublisher(), request.getPubYear(), request.getPrice(), request.getCategory(),
                    request.getStock(), request.getAuthorName(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByISBN(SearchByRequest request) {
        try {
            StringKey key = (StringKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_isbn(?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getString(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByTitle(SearchByRequest request) {
        try {
            StringKey key = (StringKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_title(?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getString(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByPublisher(SearchByRequest request) {
        try {
            StringKey key = (StringKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_publisher(?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getString(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByPubYear(SearchByRequest request) {
        try {
            YearKey key = (YearKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_pub_year(?, ?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getYear(), request.getMethod(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BookInfoResponse> searchBookByPrice(SearchByRequest request) {
        try {
            IntKey key = (IntKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_price(?, ?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getInteger(), request.getMethod(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BookInfoResponse> searchBookByStock(SearchByRequest request) {
        try {
            IntKey key = (IntKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_stock(?, ?, ?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getInteger(), request.getMethod(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByCategory(SearchByRequest request) {
        try {
            StringKey key = (StringKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_category(?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getString(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookInfoResponse> searchBookByAuthor(SearchByRequest request) {
        try {
            StringKey key = (StringKey) request.getKey();
            return jdbcTemplate.query("CALL search_book_by_author(?, ?, ?)", (rs, rowNum) -> {
                BookInfoResponse book = new BookInfoResponse();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPubYear(rs.getInt("Pub_Year"));
                book.setCategory(rs.getString("Category"));
                book.setStock(rs.getInt("Stock"));
                book.setThreshold(rs.getInt("Threshold"));
                book.setImageURL(rs.getString("Image_URL"));
                return book;
            }, key.getString(), request.getPageNumber(), request.getCountInPage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
