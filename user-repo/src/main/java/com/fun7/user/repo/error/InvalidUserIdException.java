package com.fun7.user.repo.error;

public class InvalidUserIdException extends IllegalArgumentException{
    public InvalidUserIdException(String s) {
        super(String.format("Incorrect userId: ", s));
    }
}
