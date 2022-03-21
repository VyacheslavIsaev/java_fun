package com.fun7.rest.user.api;

import com.fun7.rest.user.bl.UserService;
import com.fun7.rest.user.models.UserFeaturesResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    private static final Logger LOG = LoggerFactory.getLogger(UserApiController.class);
    private static final String VALIDATOR_CHAR_NUM = "[\\w\\d]*";
    private static final String VALIDATOR_TIMEZONE = "[\\w\\d+]*";
    private static final String MEDIATYPE_V1 = "application/vnd.api.v1+json";

    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user features.")
    @ApiResponses(value={
        @ApiResponse( responseCode = "200", description="Successful response.",
                content={
                @Content(
                        mediaType = MEDIATYPE_V1,
                        schema = @Schema(implementation = UserFeaturesResponseModel.class)
                )
                }
        ), @ApiResponse(responseCode = "500", description="Internal server error.")
    })

    @GetMapping(value="", produces = MEDIATYPE_V1 )
    public @ResponseBody
    ResponseEntity<UserFeaturesResponseModel> getFeatures(@RequestParam @Pattern(regexp = VALIDATOR_TIMEZONE) String timezone,
                                                          @RequestParam @Pattern(regexp = VALIDATOR_CHAR_NUM) String userId,
                                                          @RequestParam @Pattern(regexp = VALIDATOR_CHAR_NUM) String cc ){
        UserFeaturesResponseModel response = userService.getFeatures(userId, cc, timezone);
        return ResponseEntity.ok(response);
    }

}
