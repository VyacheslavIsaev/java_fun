package com.fun7.user.repo;

import com.fun7.user.repo.models.UserModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersRepoTest {

    UsersRepo usersRepo = new UsersRepo();

    @Test
    void user_appears_after_first_call(){
        String user = "user0";
        assertEquals(1, usersRepo.getCallsNumber(user));
        assertEquals(1, usersRepo.listAllUsers().size());
    }

    @Test
    void two_user_after_two_calls(){
        String user1 = "user0";
        String user2 = "user1";
        assertEquals(1, usersRepo.getCallsNumber(user1));
        assertEquals(1, usersRepo.getCallsNumber(user2));
        assertEquals(2, usersRepo.listAllUsers().size());
    }

    @Test
    void correct_user_returned(){
        String user1 = "user0";
        String user2 = "user1";
        assertEquals(1, usersRepo.getCallsNumber(user1));
        assertEquals(1, usersRepo.getCallsNumber(user2));
        assertEquals(2, usersRepo.listAllUsers().size());
        assertEquals(1, usersRepo.getUser(user1).getVisits());
        assertEquals(user1, usersRepo.getUser(user1).getUserId());
    }

    @Test
    void calls_number_increase_for_user(){
        String user = "user0";
        assertEquals(1, usersRepo.getCallsNumber(user));
        assertEquals(2, usersRepo.getCallsNumber(user));
        assertEquals(3, usersRepo.getCallsNumber(user));
    }

    @Test
    void correct_user_is_deleted(){
        String user1 = "user0";
        String user2 = "user1";
        assertEquals(1, usersRepo.getCallsNumber(user1));
        assertEquals(1, usersRepo.getCallsNumber(user2));
        assertEquals(2, usersRepo.listAllUsers().size());
        usersRepo.deleteUser(user1);
        List<UserModel> list = usersRepo.listAllUsers();
        assertEquals(1, list.size());
        UserModel user = list.get(0);
        assertEquals(user2, user.getUserId());
    }
}
