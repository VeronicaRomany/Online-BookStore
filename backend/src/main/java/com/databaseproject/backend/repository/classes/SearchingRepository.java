package com.databaseproject.backend.repository.classes;

import com.databaseproject.backend.repository.interfaces.ISearchingRepository;
import com.databaseproject.backend.request.SearchByRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import com.databaseproject.backend.searchkey.DetailedBookKey;
import com.databaseproject.backend.searchkey.IntKey;
import com.databaseproject.backend.searchkey.StringKey;
import com.databaseproject.backend.searchkey.YearKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchingRepository implements ISearchingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserInfoResponse> searchUsers(SearchByRequest request) {
        StringKey key = (StringKey) request.getKey();
        return jdbcTemplate.query("CALL search_user(?, ?, ?, ?)", (rs, rowNum) -> {
            UserInfoResponse user = new UserInfoResponse();
            user.setUsername(rs.getString("Username"));
            user.setFirstName(rs.getString("FName"));
            user.setLastName(rs.getString("LName"));
            user.setEmail(rs.getString("Email"));
            user.setPhone(rs.getString("Phone"));
            return user;
        }, key.getString(), request.getOrder(), request.getPageNumber(), request.getCountInPage());
    }

    @Override
    public List<BookInfoResponse> searchBooks(SearchByRequest request) {
        DetailedBookKey key = (DetailedBookKey) request.getKey();
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
                }, key.getISBN(), key.getTitle(), key.getPublisher(), key.getPubYear(), key.getPrice(), key.getCategory(),
                key.getStock(), key.getAuthorName(), request.getPageNumber(), request.getCountInPage());
    }

    @Override
    public List<BookInfoResponse> searchBookByISBN(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByTitle(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByPublisher(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByPubYear(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByPrice(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByStock(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByCategory(SearchByRequest request) {
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
    }

    @Override
    public List<BookInfoResponse> searchBookByAuthor(SearchByRequest request) {
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
    }

}
