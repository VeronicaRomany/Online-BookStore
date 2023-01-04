package com.databaseproject.backend.repository.interfaces;

import com.databaseproject.backend.request.SearchByRequest;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.UserInfoResponse;

import java.util.List;

public interface ISearchingRepository {
    List<UserInfoResponse> searchUsers(SearchByRequest request);

    List<BookInfoResponse> searchBooks(SearchByRequest request);

    List<BookInfoResponse> searchBookByISBN(SearchByRequest request);

    List<BookInfoResponse> searchBookByTitle(SearchByRequest request);

    List<BookInfoResponse> searchBookByPublisher(SearchByRequest request);

    List<BookInfoResponse> searchBookByPubYear(SearchByRequest request);

    List<BookInfoResponse> searchBookByPrice(SearchByRequest request);

    List<BookInfoResponse> searchBookByStock(SearchByRequest request);

    List<BookInfoResponse> searchBookByCategory(SearchByRequest request);

    List<BookInfoResponse> searchBookByAuthor(SearchByRequest request);

}
