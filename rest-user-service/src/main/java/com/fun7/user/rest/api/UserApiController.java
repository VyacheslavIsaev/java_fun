package com.fun7.user.rest.api;

import com.fun7.user.rest.bl.UserService;
import com.fun7.user.rest.models.UserFeaturesResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    private static final Logger LOG = LoggerFactory.getLogger(UserApiController.class);

    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public @ResponseBody
    ResponseEntity<UserFeaturesResponseModel> getFeatures(@RequestParam @NotEmpty String timezone,
                                                          @RequestParam @NotEmpty String userId,
                                                          @RequestParam @NotEmpty String cc ){
        UserFeaturesResponseModel response = userService.getFeatures(userId, cc, timezone);
        return ResponseEntity.ok(response);
    }

}
