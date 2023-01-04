package com.databaseproject.backend.controller;

import com.databaseproject.backend.repository.interfaces.ISearchingRepository;
import com.databaseproject.backend.request.SearchByRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class SearchingController {
    private final ISearchingRepository searchingRepository;

    private final String test = "/test";

    @Autowired
    public SearchingController(ISearchingRepository searchingRepository) {
        this.searchingRepository = searchingRepository;
    }

    @GetMapping(test + "/users")
    ResponseEntity<List<UserInfoResponse>> searchUsers(@RequestBody SearchByRequest criteria,
                                                       Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchUsers(criteria));
    }

    @GetMapping(test +  "/books-by-details")
    ResponseEntity<List<BookInfoResponse>> searchBooks(@RequestBody SearchByRequest criteria,
                                                       Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBooks(criteria));
    }


    @GetMapping(test + "/books-by-ISBN")
    ResponseEntity<List<BookInfoResponse>> searchBookByISBN(@RequestBody SearchByRequest criteria,
                                                            Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByISBN(criteria));
    }

    @GetMapping(test + "/books-by-title")
    ResponseEntity<List<BookInfoResponse>> searchBookByTitle(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByTitle(criteria));
    }

    @GetMapping(test + "/books-by-publisher")
    ResponseEntity<List<BookInfoResponse>> searchBookByPublisher(@RequestBody SearchByRequest criteria,
                                                                 Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByPublisher(criteria));
    }

    @GetMapping(test + "/books-by-publication-year")
    ResponseEntity<List<BookInfoResponse>> searchBookByPubYear(@RequestBody SearchByRequest criteria,
                                                               Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByPubYear(criteria));
    }

    @GetMapping(test + "/books-by-price")
    ResponseEntity<List<BookInfoResponse>> searchBookByPrice(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByPrice(criteria));
    }

    @GetMapping(test + "/books-by-stock")
    ResponseEntity<List<BookInfoResponse>> searchBookByStock(@RequestBody SearchByRequest criteria,
                                                             Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByStock(criteria));
    }

    @GetMapping(test + "/books-by-category")
    ResponseEntity<List<BookInfoResponse>> searchBookByCategory(@RequestBody SearchByRequest criteria,
                                                                Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByCategory(criteria));
    }

    @GetMapping(test + "/books-by-author")
    ResponseEntity<List<BookInfoResponse>> searchBookByAuthor(SearchByRequest criteria,
                                                              @RequestBody Authentication auth) {
        return ResponseEntity.ok(searchingRepository.searchBookByAuthor(criteria));
    }

}
