package com.fun7.rest.user.bl.impl;

import com.fun7.feature.ads.AdsFeature;
import com.fun7.feature.customer.support.CustomerSupportFeature;
import com.fun7.feature.multiplayer.MultiplayerFeature;
import com.fun7.rest.user.bl.UserService;
import com.fun7.rest.user.models.UserFeaturesResponseModel;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private AdsFeature ads;

    private CustomerSupportFeature customerSupport;

    private MultiplayerFeature multiplayerFeature;

    @Override
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone) {
        return new UserFeaturesResponseModel();
    }

}
