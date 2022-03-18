package com.fun7.feature.ads;

import com.fun7.feature.ads.config.FeatureAdsConfig;
import com.fun7.feature.ads.config.FeatureAdsConfigData;
import com.fun7.feature.ads.model.ExternalAdsResponseModel;
import com.fun7.feature.impl.FeatureUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeatureAds extends FeatureUtils {

    private final RestTemplate restTemplate;

    private final String requestUrl;
    private final String userName;
    private final String userPass;
    private final String adsEnabledStr;

    public FeatureAds(RestTemplate restTemplate, FeatureAdsConfigData configData) {
        this.restTemplate = restTemplate;
        requestUrl = configData.getRequestUrl();
        userName = configData.getUserName();
        userPass = configData.getUserPass();
        adsEnabledStr = configData.getEnabledStr();
    }

    @CircuitBreaker(name = "externalApiCB")
    public String callExternalApi(String cc){
        HttpEntity request = getRequestBasicAuthentication();

        ResponseEntity<ExternalAdsResponseModel> response = restTemplate.exchange(requestUrl,
                HttpMethod.GET,
                request,
                ExternalAdsResponseModel.class,
                cc);
        return response.getBody().getAds();
    }

    private HttpEntity getRequestBasicAuthentication() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userName, userPass);
        return new HttpEntity(headers);
    }

    @Override
    public boolean enabled(String userId, String cc, String timezone) {
        return callExternalApi(cc).equals(adsEnabledStr);
    }
}
