package com.fun7.feature.ads.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(
        classes={
                AdsFeatureConfigData.class
        })
@WebAppConfiguration
public class AdsFeatureConfigDataTest {

    @Autowired
    AdsFeatureConfigData data;

    @Test
    public void context(){
        assertNotNull(data);
    }

    @Test
    public void data_matches(){
        assertNotNull(data.getRootUri());
        assertNotNull(data.getRequestUrl() );
        assertNotNull(data.getUserName() );
        assertNotNull(data.getUserPass() );
        assertNotNull(data.getEnabledStr() );
    }
}
