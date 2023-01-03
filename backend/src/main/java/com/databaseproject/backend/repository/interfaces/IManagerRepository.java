package com.databaseproject.backend.repository.interfaces;

import com.databaseproject.backend.request.*;

import java.sql.CallableStatement;

public interface IManagerRepository {
    boolean confirmLibraryOrder(ConfirmLibraryOrderRequest request);

    boolean createLibraryOrder(CreateLibraryOrderRequest request);

    boolean promoteUser(PromoteUserRequest request, String managerUsername);

    boolean modifyBook(ModifyBookRequest request);

    boolean addPublisher(AddPublisherRequest request);

    boolean addBook(AddBookRequest request);

}
