package com.fun7.feature;

import com.fun7.feature.impl.FeatureUtils;
import com.fun7.feature.impl.FeatureUtilsMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeatureUtilsTest {

    private final FeatureUtilsMock utilsMock = new FeatureUtilsMock();

    @Test
    void enabled_returns_true_when_user_enabled(){
        assertTrue(utilsMock.enabled(FeatureUtils.ENABLED,"",""));
    }

    @Test
    void enabled_returns_false_when_user_is_not_enabled(){
        assertFalse(utilsMock.enabled("","",""));
        assertFalse(utilsMock.enabled(FeatureUtils.DISABLED,"",""));
    }

    @Test
    void returns_enabled_when_true(){
        assertEquals(utilsMock.is_enabled_str(FeatureUtils.ENABLED,"",""), FeatureUtils.ENABLED);
    }

    @Test
    void returns_disabled_when_false(){
        assertEquals(utilsMock.is_enabled_str("","",""), FeatureUtils.DISABLED);
        assertEquals(utilsMock.is_enabled_str(FeatureUtils.DISABLED,"",""), FeatureUtils.DISABLED);
    }

}
