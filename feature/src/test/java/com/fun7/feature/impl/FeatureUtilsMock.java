package com.fun7.feature.impl;

public class FeatureUtilsMock extends FeatureUtils {

    @Override
    public boolean enabled(String userId, String cc, String timezone) {
        return userId.equals(FeatureUtils.ENABLED);
    }

}
