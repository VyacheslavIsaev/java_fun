package com.fun7.feature.multiplayer;

import com.fun7.feature.impl.FeatureUtils;
import com.fun7.user.repo.UserInterface;
import org.springframework.stereotype.Component;

@Component
public class MultiplayerFeature extends FeatureUtils {

    public static Integer EXPERIENCED_USER_TIMES = 5;
    public static final String SUPPORTED_REGION = "us";

    private UserInterface usersRepo;

    public MultiplayerFeature(UserInterface usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public boolean enabled(String userId, String cc, String timezone) {
        return isUserExperienced(userId) && isRegionSupported(cc);
    }

    protected boolean isUserExperienced(String userId){
        return usersRepo.getVisitsNumber(userId) > EXPERIENCED_USER_TIMES;
    }

    protected boolean isRegionSupported(String userId){
        return userId.toLowerCase().equals(SUPPORTED_REGION);
    }
}
