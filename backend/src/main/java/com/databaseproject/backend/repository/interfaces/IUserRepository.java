package com.databaseproject.backend.repository.interfaces;

import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.ModifyUserRequest;

public interface IUserRepository {
    boolean modifyUser(ModifyUserRequest request);
    boolean createUserOrder(CreateOrderRequest request, String username);

}
