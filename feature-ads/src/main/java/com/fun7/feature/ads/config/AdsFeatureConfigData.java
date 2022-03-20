package com.fun7.feature.ads.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AdsFeatureConfigData {
    @Value("${ads-feature.root-uri}")
    private String rootUri;

    @Value("${ads-feature.request-url}")
    private String requestUrl;

    @Value("${ads-feature.user-name}")
    private String userName;

    @Value("${ads-feature.user-pass}")
    private String userPass;

    @Value("${ads-feature.enabled-str}")
    private String enabledStr;
}
