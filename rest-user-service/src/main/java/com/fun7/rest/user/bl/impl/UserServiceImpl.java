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

    public UserServiceImpl(AdsFeature ads) {
        this.ads = ads;
    }

    @Override
    public UserFeaturesResponseModel getFeatures(String userId, String cc, String timezone) {
        String adsStatus = ads.is_enabled_str(userId, cc, timezone);
        String csStatus = customerSupport.is_enabled_str(userId, cc, timezone);
        String mutiplayerStatus = multiplayerFeature.is_enabled_str(userId, cc, timezone);
        return new UserFeaturesResponseModel().builder()
                .multiplayer(mutiplayerStatus)
                .user_support(csStatus)
                .ads(adsStatus)
                .build();
    }

}
