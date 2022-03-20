package com.fun7.feature.multiplayer;

import com.fun7.user.repo.UsersRepo;

public class MultiplayerFeatureSpy extends MultiplayerFeature{

    public MultiplayerFeatureSpy(UsersRepo usersRepo) {
        super(usersRepo);
    }

    public boolean isUserExperiencedPub(String userId){
        return isUserExperienced(userId);
    }

    public boolean isRegionSupportedPub(String cc){
        return isRegionSupported(cc);
    }
}
