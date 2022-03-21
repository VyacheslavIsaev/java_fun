package com.fun7.feature.ads;

import com.fun7.feature.ads.config.AdsFeatureConfigData;
import com.fun7.feature.ads.model.ExternalAdsResponseModel;
import com.fun7.feature.impl.FeatureUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.fun7.feature.ads.config.AdsFeatureConfig.RESILIENCE4J_CB_INSTANCE_NAME;

@Service
public class AdsFeature extends FeatureUtils {

    private final RestTemplate restTemplate;

    private final String requestUrl;
    private final String userName;
    private final String userPass;
    private final String adsEnabledStr;

    public AdsFeature(RestTemplate restTemplate, AdsFeatureConfigData configData) {
        this.restTemplate = restTemplate;
        requestUrl = configData.getRequestUrl();
        userName = configData.getUserName();
        userPass = configData.getUserPass();
        adsEnabledStr = configData.getEnabledStr();
    }

    @Retry(name = "externalApiRetry")
    @CircuitBreaker(name = RESILIENCE4J_CB_INSTANCE_NAME)
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
        // Feature: Implement time-based expiring caching
        // result shall depend on the exception type.
        try {
            return callExternalApi(cc).equals(adsEnabledStr);
        }catch(Exception e){
            return false;
        }
    }
}
