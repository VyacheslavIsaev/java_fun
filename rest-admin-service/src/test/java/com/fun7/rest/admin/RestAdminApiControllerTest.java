package com.fun7.rest.admin;

import com.fun7.rest.admin.api.RestAdminApiController;
import com.fun7.user.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(UsersRepo.class)
@WebMvcTest(controllers = RestAdminApiController.class)
public class RestAdminApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepo usersRepo;

    @Test
    public void returns_list_of_users() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void returns_not_found_when_specific_user_not_exists() throws Exception {
        String user = "userXXX";
        this.mockMvc.perform(get("/api/users/"+user)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void returns_specific_user() throws Exception {
        String user = "user1";
        usersRepo.getVisitsNumber(user);
        this.mockMvc.perform(get("/api/users/"+user)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userId\":\"user1\",\"visits\":1}"));
    }

    @Test
    public void specific_user_is_absent_when_deleted() throws Exception {
        String user = "user2";
        usersRepo.getVisitsNumber(user);
        this.mockMvc.perform(get("/api/users/"+user)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userId\":"+user+",\"visits\":1}"));
        this.mockMvc.perform(delete("/api/users/"+user))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/api/users/"+user)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
