package com.fun7.feature.multiplayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplayerFeatureTest {

    private final MultiplayerFeatureSpy multiplayerFeature = new MultiplayerFeatureSpy();

    @Test
    void calls_number_increase_for_user(){
        String user = "user0";
        assertEquals(1, multiplayerFeature.getCallsNumberPub(user));
        assertEquals(2, multiplayerFeature.getCallsNumberPub(user));
    }

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
