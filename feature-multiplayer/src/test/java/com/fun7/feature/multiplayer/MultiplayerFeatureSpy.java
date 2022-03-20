package com.fun7.feature.multiplayer;

import com.fun7.user.repo.Repo;

public class MultiplayerFeatureSpy extends MultiplayerFeature{

    public MultiplayerFeatureSpy(Repo usersRepo) {
        super(usersRepo);
    }

    public boolean isUserExperiencedPub(String userId){
        return isUserExperienced(userId);
    }

    public boolean isRegionSupportedPub(String cc){
        return isRegionSupported(cc);
    }
}
