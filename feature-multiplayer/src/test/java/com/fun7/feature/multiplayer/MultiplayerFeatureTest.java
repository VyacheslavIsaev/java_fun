package com.fun7.feature.multiplayer;

import com.fun7.user.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {com.fun7.user.repo.UsersRepo.class})
public class MultiplayerFeatureTest {

    @Autowired
    private UsersRepo usersRepo;

    private final MultiplayerFeatureSpy multiplayerFeature = new MultiplayerFeatureSpy(usersRepo);

    @Test
    void false_when_user_is_not_experienced(){
        String user = "user1";
        for (int i=0; i<MultiplayerFeature.EXPERIENCED_USER_TIMES-1; ++i )
            assertFalse(multiplayerFeature.isUserExperiencedPub(user));
    }

    @Test
    void true_when_user_called_more_then_required_times(){
        String user = "user2";
        for (int i=0; i<MultiplayerFeature.EXPERIENCED_USER_TIMES; ++i )
            assertFalse(multiplayerFeature.isUserExperiencedPub(user));
        assertTrue(multiplayerFeature.isUserExperiencedPub(user));
    }

    @Test
    void true_if_region_is_us(){
        assertTrue(multiplayerFeature.isRegionSupportedPub("US"));
        assertTrue(multiplayerFeature.isRegionSupportedPub("us"));
    }

    @Test
    void false_if_region_is_not_us(){
        assertFalse(multiplayerFeature.isRegionSupportedPub("SI"));
        assertFalse(multiplayerFeature.isRegionSupportedPub("si"));
    }

}
