package com.fun7.rest.admin.api;

import com.fun7.user.repo.UsersAdminService;
import com.fun7.user.repo.models.UserModel;
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
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class AdminApiController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminApiController.class);

    private UsersAdminService usersAdminService;

    public AdminApiController(UsersAdminService usersAdminService) {
        this.usersAdminService = usersAdminService;
    }

    @Operation(summary = "Get list of all users.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = "application/vnd.api.v1+json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserModel.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @GetMapping(value="/users", produces = "application/vnd.api.v1+json")
    public @ResponseBody
    ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> response = usersAdminService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by Id.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = "application/vnd.api.v1+json",
                                    schema = @Schema(implementation = UserModel.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @GetMapping(value="/users/{id}", produces = "application/vnd.api.v1+json")
    public @ResponseBody
    ResponseEntity<UserModel> getUser(@PathVariable @NotEmpty String id){
        LOG.info("getUser with id {}", id);
        UserModel response = usersAdminService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user with specified Id.")
    @ApiResponses(value={
            @ApiResponse( responseCode = "200", description="Successful response.",
                    content={
                            @Content(
                                    mediaType = "application/vnd.api.v1+json",
                                    schema = @Schema(implementation = UserModel.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description="Not found."),
            @ApiResponse(responseCode = "500", description="Internal server error.")
    })
    @DeleteMapping(value="/users/{id}", produces = "application/vnd.api.v1+json")
    public @ResponseStatus
    ResponseEntity<String> deleteUser(@PathVariable @NotEmpty String id){
        usersAdminService.deleteUser(id);
        return ResponseEntity.ok("");
    }

}
