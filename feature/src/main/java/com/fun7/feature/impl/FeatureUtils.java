package com.fun7.feature.impl;

import com.fun7.feature.Feature;

abstract public class FeatureUtils implements Feature {

    public static final String ENABLED  = "enabled";
    public static final String DISABLED = "disabled";

    public String is_enabled_str(String userId, String cc, String timezone) {
        return (enabled(userId, cc, timezone)) ? ENABLED : DISABLED;
    }
}
