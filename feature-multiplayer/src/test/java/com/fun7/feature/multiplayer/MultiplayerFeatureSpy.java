package com.fun7.feature.multiplayer;

public class MultiplayerFeatureSpy extends MultiplayerFeature{

    public boolean isUserExperiencedPub(String userId){
        return isUserExperienced(userId);
    }

    public boolean isRegionSupportedPub(String cc){
        return isRegionSupported(cc);
    }

    public Integer getCallsNumberPub(String userId){
        return getCallsNumber(userId);
    }
}
