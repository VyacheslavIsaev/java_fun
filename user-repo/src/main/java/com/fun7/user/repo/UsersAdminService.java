package com.fun7.user.repo;

import com.fun7.user.repo.models.UserModel;

import java.util.List;

public interface UsersAdminService {
    List<UserModel> getAllUsers();

    UserModel getUser(String id);

    void deleteUser(String id);
}
