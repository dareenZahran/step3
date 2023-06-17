package com.dareen.spu.User;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("user with ID " + userId + " not found");
    }
}


