package com.fun7.rest.admin.api;

import com.fun7.rest.admin.models.UserIdResponseModel;
import com.fun7.user.repo.UsersAdminService;
import com.fun7.user.repo.models.UserDataModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class RestAdminApiController {
    private static final Logger LOG = LoggerFactory.getLogger(RestAdminApiController.class);
    private static final String MEDIATYPE_V1 = "application/vnd.api.v1+json";
    private static final String VALIDATOR_ISER_ID = "[\\w\\d]*";

    private UsersAdminService usersAdminService;

    public RestAdminApiController(UsersAdminService usersAdminService) {
        this.usersAdminService = usersAdminService;
    }

    @Operation(summary = "Get list of all users.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = MEDIATYPE_V1,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDataModel.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @GetMapping(value="/users", produces = MEDIATYPE_V1)
    public @ResponseBody
    ResponseEntity<List<UserDataModel>> getAllUsers(){
        List<UserDataModel> response = usersAdminService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by Id.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = MEDIATYPE_V1,
                                    schema = @Schema(implementation = UserDataModel.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @GetMapping(value="/users/{id}", produces = MEDIATYPE_V1)
    public @ResponseBody
    ResponseEntity<UserDataModel> getUser(@PathVariable @NotEmpty @Pattern(regexp = VALIDATOR_ISER_ID) String id){
        LOG.info("getUser with id {}", id);
        UserDataModel response = usersAdminService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user with specified Id.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = MEDIATYPE_V1,
                                    schema = @Schema(implementation = UserDataModel.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @DeleteMapping(value="/users/{id}", produces = MEDIATYPE_V1)
    public @ResponseStatus
    ResponseEntity<UserIdResponseModel> deleteUser(@PathVariable @NotEmpty @Pattern(regexp = VALIDATOR_ISER_ID) String id){
        usersAdminService.deleteUser(id);
        return ResponseEntity.accepted().body(new UserIdResponseModel(id));
    }

}
