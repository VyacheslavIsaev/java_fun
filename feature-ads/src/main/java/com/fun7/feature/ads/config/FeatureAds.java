package com.fun7.feature.ads.config;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeatureAds {
    private final RestTemplate restTemplate;
    private final String URL = "/user";

    public FeatureAds(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "externalApiCB")
    public String callExternalApi(){
        return restTemplate.getForObject("", String.class);
    }

}
