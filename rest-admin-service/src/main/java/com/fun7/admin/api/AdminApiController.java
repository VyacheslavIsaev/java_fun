package com.fun7.admin.api;


import com.fun7.admin.bl.UsersAdminService;
import com.fun7.admin.models.UserResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping
public class AdminApiController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminApiController.class);

    private UsersAdminService usersAdminService;

    public AdminApiController(UsersAdminService usersAdminService) {
        this.usersAdminService = usersAdminService;
    }

    @GetMapping("/users")
    public @ResponseBody
    ResponseEntity<List<UserResponseModel>> getAllUsers(){
        List<UserResponseModel> response = usersAdminService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public @ResponseBody
    ResponseEntity<UserResponseModel> getUser(@PathVariable @NotEmpty String id){
        LOG.info("getUser with id {}", id);
        UserResponseModel response = usersAdminService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public @ResponseStatus
    ResponseEntity<String> deleteUser(@PathVariable @NotEmpty String id){
        boolean res = usersAdminService.deleteUser(id);
        return ResponseEntity.ok("");
    }

}
