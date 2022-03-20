package com.fun7.user.repo;

import com.fun7.user.repo.models.UserDataModel;

import java.util.List;

public interface AdminUserInterface {
    List<UserDataModel> getAllUsers();

    UserDataModel getUser(String id);

    void deleteUser(String id);
}
