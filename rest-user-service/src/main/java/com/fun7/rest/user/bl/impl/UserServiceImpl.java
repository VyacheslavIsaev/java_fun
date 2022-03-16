package com.fun7.rest.user.bl.impl;

import com.fun7.rest.user.bl.UserService;
import com.fun7.rest.user.models.UserFeaturesResponseModel;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone) {
        return new UserFeaturesResponseModel();
    }
}
