package com.fun7.user.rest.bl.impl;

import com.fun7.user.rest.bl.UserService;
import com.fun7.user.rest.models.UserFeaturesResponseModel;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone) {
        return new UserFeaturesResponseModel();
    }
}
