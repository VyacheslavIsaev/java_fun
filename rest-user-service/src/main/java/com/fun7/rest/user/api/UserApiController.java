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

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    private static final Logger LOG = LoggerFactory.getLogger(UserApiController.class);

    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user features.")
    @ApiResponses(value={
        @ApiResponse( responseCode = "200", description="Successful response.",
                content={
                @Content(
                        mediaType = "application/vnd.api.v1+json",
                        schema = @Schema(implementation = UserFeaturesResponseModel.class)
                )
                }
        ), @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @GetMapping(value="", produces = "application/vnd.api.v1+json" )
    public @ResponseBody
    ResponseEntity<UserFeaturesResponseModel> getFeatures(@RequestParam @NotEmpty String timezone,
                                                          @RequestParam @NotEmpty String userId,
                                                          @RequestParam @NotEmpty String cc ){
        UserFeaturesResponseModel response = userService.getFeatures(userId, cc, timezone);
        return ResponseEntity.ok(response);
    }

}
