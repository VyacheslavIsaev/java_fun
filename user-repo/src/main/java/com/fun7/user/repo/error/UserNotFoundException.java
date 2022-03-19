package com.fun7.user.repo.error;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super(String.format("User not found: %s", userId));
    }
}
