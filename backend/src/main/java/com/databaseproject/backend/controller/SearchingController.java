package com.databaseproject.backend.controller;

import com.databaseproject.backend.repository.interfaces.ISearchingRepository;
import com.databaseproject.backend.request.DetailedBookSearchRequest;
import com.databaseproject.backend.request.SearchByRequest;
import com.databaseproject.backend.request.UserSearchRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/search")
public class SearchingController {
    private final ISearchingRepository searchingRepository;

    private final String test = "";

    @Autowired
    public SearchingController(ISearchingRepository searchingRepository) {
        this.searchingRepository = searchingRepository;
    }

    @GetMapping(test + "/users")
    ResponseEntity<List<UserInfoResponse>> searchUsers(@RequestBody UserSearchRequest criteria,
                                                       Authentication auth) {
        List<UserInfoResponse> users = searchingRepository.searchUsers(criteria);

        if(users != null)
            return ResponseEntity.ok(users);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PostMapping(test +  "/books-by-details")
    ResponseEntity<List<BookInfoResponse>> searchBooks(@RequestBody DetailedBookSearchRequest criteria,
                                                       Authentication auth) {
        System.out.println(criteria);
        List<BookInfoResponse> books = searchingRepository.searchBooks(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    @GetMapping(test + "/books-by-ISBN")
    ResponseEntity<List<BookInfoResponse>> searchBookByISBN(@RequestBody SearchByRequest criteria,
                                                            Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByISBN(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-title")
    ResponseEntity<List<BookInfoResponse>> searchBookByTitle(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByTitle(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-publisher")
    ResponseEntity<List<BookInfoResponse>> searchBookByPublisher(@RequestBody SearchByRequest criteria,
                                                                 Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByPublisher(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-publication-year")
    ResponseEntity<List<BookInfoResponse>> searchBookByPubYear(@RequestBody SearchByRequest criteria,
                                                               Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByPubYear(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-price")
    ResponseEntity<List<BookInfoResponse>> searchBookByPrice(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByPrice(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-stock")
    ResponseEntity<List<BookInfoResponse>> searchBookByStock(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByStock(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-category")
    ResponseEntity<List<BookInfoResponse>> searchBookByCategory(@RequestBody SearchByRequest criteria,
                                                                Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByCategory(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(test + "/books-by-author")
    ResponseEntity<List<BookInfoResponse>> searchBookByAuthor(SearchByRequest criteria,
                                                              @RequestBody Authentication auth) {
        List<BookInfoResponse> books = searchingRepository.searchBookByAuthor(criteria);

        if(books != null)
            return ResponseEntity.ok(books);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
