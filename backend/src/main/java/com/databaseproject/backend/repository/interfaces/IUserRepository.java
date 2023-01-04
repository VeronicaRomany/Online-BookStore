package com.databaseproject.backend.repository.interfaces;

import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.ModifyUserRequest;
import com.databaseproject.backend.request.UserRequest;
import com.databaseproject.backend.response.UserInfoResponse;

public interface IUserRepository {
    boolean createUser(UserRequest userRequest);

    boolean modifyUser(ModifyUserRequest request);

    Integer createUserOrder(CreateOrderRequest request, String username);

    UserInfoResponse getUserInfo(String username);

}
