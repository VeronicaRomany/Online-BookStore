package com.databaseproject.backend.repository.interfaces;

import com.databaseproject.backend.request.*;
import com.databaseproject.backend.response.BookInfoResponse;
import com.databaseproject.backend.response.LibraryOrderResponse;

import java.sql.CallableStatement;
import java.util.List;

public interface IManagerRepository {
    boolean confirmLibraryOrder(ConfirmLibraryOrderRequest request);

    boolean createLibraryOrder(CreateLibraryOrderRequest request);

    boolean promoteUser(PromoteUserRequest request, String managerUsername);

    boolean modifyBook(ModifyBookRequest request);

    boolean addPublisher(AddPublisherRequest request);

    boolean addBook(AddBookRequest request);

    BookInfoResponse findBookByISBN(String ISBN);

    List<LibraryOrderResponse> getLibraryOrders();

}
