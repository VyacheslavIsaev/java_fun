package com.fun7.feature.ads;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes={com.fun7.feature.ads.config.FeatureAdsConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeatureAdsServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        this.wireMockServer = new WireMockServer(
                options()
                        .extensions(new ResponseTemplateTransformer(false))
                        .port(9090));
        this.wireMockServer.stubFor(get(urlEqualTo("/test-url"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/json")
                        .withStatus(200)
                        .withBody("{{request.url}}")
                        .withTransformers("response-template"))
        );
        this.wireMockServer.start();
    }

    @Test
    public void testFoo() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/test-url", String.class);
        assertEquals( HttpStatus.OK, response.getStatusCode());
    }
}
