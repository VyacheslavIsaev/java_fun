package com.fun7.rest.user.bl.impl;

import com.fun7.rest.user.models.UserFeaturesResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserInterfaceImplTest {

    public static final String ENABLED="enabled";
    public static final String DISABLED="disabled";

    @Autowired
    UserServiceImpl impl;

    @Test
    public void multiplayer_behaves_as_expected(){
        String user1 = "user1";
        String cc = "us";
        String timezone = "PCT";
        for (int i=0; i<5; i++) {
            UserFeaturesResponseModel response = impl.getFeatures(user1, cc, timezone);
            assertEquals(DISABLED, response.getMultiplayer());
            assertEquals(DISABLED, response.getAds());
        }
        UserFeaturesResponseModel response = impl.getFeatures(user1, cc, timezone);
        assertEquals(ENABLED, response.getMultiplayer());
        assertEquals(DISABLED, response.getAds());
    }

}
