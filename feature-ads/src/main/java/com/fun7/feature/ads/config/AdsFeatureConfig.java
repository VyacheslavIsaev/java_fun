package com.fun7.feature.ads.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class AdsFeatureConfig {

    public static final String RESILIENCE4J_CB_INSTANCE_NAME = "externalApiCB";

    AdsFeatureConfigData configData;

    public AdsFeatureConfig(AdsFeatureConfigData configData) {
        this.configData = configData;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(configData.getRootUri()).build();
    }

//    @Bean
//    public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
//        return CircuitBreakerConfigCustomizer
//                .of(RESILIENCE4J_CB_INSTANCE_NAME,
//                        builder -> builder.slidingWindowSize(10)
//                                .slidingWindowType(COUNT_BASED)
//                                .waitDurationInOpenState(Duration.ofSeconds(5))
//                                .minimumNumberOfCalls(5)
//                                .failureRateThreshold(50.0f));
//    }
}
