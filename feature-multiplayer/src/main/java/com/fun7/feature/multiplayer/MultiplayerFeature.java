package com.fun7.feature.multiplayer;

import com.fun7.feature.Feature;
import com.fun7.feature.impl.FeatureUtils;

import java.util.HashMap;

public class MultiplayerFeature extends FeatureUtils {

    public static Integer EXPERIENCED_USER_TIMES = 5;
    public static final String SUPPORTED_REGION = "us";
    private HashMap<String, Integer> userExperience = new HashMap<String, Integer>();

    @Override
    public boolean enabled(String userId, String cc, String timezone) {
        return isUserExperienced(userId) && isRegionSupported(cc);
    }

    protected int getCallsNumber(String userId){
        Integer value = userExperience.get(userId);
        if (value == null){
            value = 1;

        }else
        {
            value++;
        }
        userExperience.put(userId, value);
        return value;
    }

    protected boolean isUserExperienced(String userId){
        return getCallsNumber(userId) > EXPERIENCED_USER_TIMES;
    }

    protected boolean isRegionSupported(String userId){
        return userId.toLowerCase().equals(SUPPORTED_REGION);
    }
}
