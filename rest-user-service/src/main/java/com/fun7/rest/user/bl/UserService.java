package com.fun7.rest.user.bl;

import com.fun7.rest.user.models.UserFeaturesResponseModel;

public interface UserService {
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone);
}
