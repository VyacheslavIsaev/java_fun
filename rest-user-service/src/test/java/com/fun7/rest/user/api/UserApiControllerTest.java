package com.fun7.rest.user.api;

import com.fun7.user.repo.UsersRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(UsersRepo.class)
@WebMvcTest
public class UserApiControllerTest {

    private static final String URL = "/api";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepo usersRepo;

    private String encodeValue(String value){
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception e)
        {
        }
        return "";
    }

    private String buildRequest(String user, String cc, String timezone) throws Exception{
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("userId", user);
        requestParams.put("cc", cc);
        requestParams.put("timezone", timezone);
        String encodedURL = requestParams.keySet().stream()
                .map(key -> key + "=" + encodeValue(requestParams.get(key)))
                .collect(joining("&", String.format("%s?", URL), ""));

        return encodedURL;
    }

    @Test
    public void returns_some_users_data() throws Exception {
        String request = buildRequest("user1", "us", "PTS");
        this.mockMvc.perform(get(request)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    @Disabled
    public void invalid_user_id() throws Exception {
        String request = buildRequest("user{", "us", "PTS");
        this.mockMvc.perform(get(request)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Disabled
    public void bad_request_on_empty_cc() throws Exception {
        String request = buildRequest("user{", " ", "PTS");
        this.mockMvc.perform(get(request)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Disabled
    public void bad_request_on_long_timezone() throws Exception {
        String request = buildRequest("user{", " ", "GMT+11");
        this.mockMvc.perform(get(request)).andDo(print())
                .andExpect(status().isBadRequest());
    }

}
