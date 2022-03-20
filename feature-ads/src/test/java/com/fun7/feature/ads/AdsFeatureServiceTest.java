package com.fun7.feature.ads;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes={
                com.fun7.feature.ads.config.FeatureAdsConfigData.class,
                com.fun7.feature.ads.config.FeatureAdsConfig.class,
                com.fun7.feature.ads.FeatureAds.class
        })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class FeatureAdsServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeatureAds featureAds;

    private WireMockServer wireMockServer;

    private static final String URL = "/fun7-ad-partner";
    private static final String MATCH_URL = URL+"*";
    private static final String REQUEST_URL = "/fun7-ad-partner?countryCode={cc}";
    private static final String USER_NAME = "fun7user";
    private static final String USER_PASS = "fun7pass";
    private static final String ENABLED_REGION_STR  = "us";
    private static final String ENABLED_STR  = "{\"ads\": \"sure, why not!\"}";
    private static final String DISABLED_STR = "{\"ads\": \"you shall not pass!\"}";

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(
                options()
                        .extensions(new ResponseTemplateTransformer(false))
                        .port(9090));
        wireMockServer.stubFor(get(urlPathMatching(MATCH_URL))
                .atPriority(1)
                .withBasicAuth(USER_NAME, USER_PASS)
                .withQueryParam("countryCode", equalTo(ENABLED_REGION_STR))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(ENABLED_STR)
                        .withTransformers("response-template"))
        );
        wireMockServer.stubFor(get(urlPathMatching(MATCH_URL))
                .atPriority(2)
                .withBasicAuth(USER_NAME, USER_PASS)
                .withQueryParam("countryCode", notMatching(ENABLED_REGION_STR))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(DISABLED_STR)
                        .withTransformers("response-template"))
        );
        wireMockServer.stubFor(get(urlPathMatching(MATCH_URL))
                .atPriority(3)
                .withBasicAuth(USER_NAME, "fun7pass")
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "text/html")
                        .withBody("!!! Missing arguments !!!")));
        wireMockServer.stubFor(get(urlPathMatching(MATCH_URL))
                .atPriority(4)
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "text/html")
                        .withBody("!!! Missing arguments !!!")));
        wireMockServer.stubFor(get(urlPathMatching(MATCH_URL))
                .atPriority(5)
                .withQueryParam("countryCode", matching(".*"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "text/html")
                        .withBody("!!! Unauthorized !!!")));
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown(){
        wireMockServer.stop();
    }

    @Test
    public void mock_server_returns_400_response() throws Exception {
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    public void mock_server_returns_401_response() throws Exception {
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<String> response = restTemplate.getForEntity(REQUEST_URL, String.class,"us");
            assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
        });
    }

    @Test
    public void mock_server_returns_Ok_response() throws Exception {
        // create headers
        HttpHeaders headers = new HttpHeaders();

        // add basic authentication header
        headers.setBasicAuth(USER_NAME, USER_PASS);

        // build the request
        HttpEntity request = new HttpEntity(headers);

        //building restTemplate
        ResponseEntity<String> response = restTemplate.exchange(REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class,
                "us");
        assertEquals( HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void disabled_if_not_enabled_region_is_passed() throws Exception {
        assertFalse( featureAds.enabled("si", "si", "si") );
    }

    @Test
    public void enabled_if_correct_region_is_passed() throws Exception {
        assertTrue( featureAds.enabled(ENABLED_REGION_STR,ENABLED_REGION_STR,ENABLED_REGION_STR) );
    }

    @Test
    public void testFoo() throws Exception {
//        EXTERNAL_SERVICE.wsxxxubFor(get("/external-foo").willReturn(serverError()));
//
//        for (int i = 0; i < 5; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity("/foo", String.class);
//            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        for (int i = 0; i < 5; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity("/foo", String.class);
//            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
//        }
    }

}
