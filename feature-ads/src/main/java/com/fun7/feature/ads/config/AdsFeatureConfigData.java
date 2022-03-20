package com.fun7.feature.ads.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AdsFeatureConfigData {
    @Value("${feature-ads-service.root-uri}")
    private String rootUri;

    @Value("${feature-ads-service.request-url}")
    private String requestUrl;

    @Value("${feature-ads-service.user-name}")
    private String userName;

    @Value("${feature-ads-service.user-pass}")
    private String userPass;

    @Value("${feature-ads-service.enabled-str}")
    private String enabledStr;
}
