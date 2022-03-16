package com.fun7.admin.bl.impl;

import com.fun7.admin.bl.UsersAdminService;
import com.fun7.admin.models.UserResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersAdminServiceImpl implements UsersAdminService {

    @Override
    public List<UserResponseModel> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseModel getUser(String id) {
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }
}
