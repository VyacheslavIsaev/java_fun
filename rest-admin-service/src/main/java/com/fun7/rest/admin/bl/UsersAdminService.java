package com.fun7.rest.admin.bl;

import com.fun7.rest.admin.models.UserResponseModel;

import java.util.List;

public interface UsersAdminService {
    List<UserResponseModel> getAllUsers();

    UserResponseModel getUser(String id);

    boolean deleteUser(String id);
}
