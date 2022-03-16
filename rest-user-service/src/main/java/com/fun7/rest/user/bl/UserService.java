package com.fun7.user.rest.bl;

import com.fun7.user.rest.models.UserFeaturesResponseModel;

public interface UserService {
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone);
}
