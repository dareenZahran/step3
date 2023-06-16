package com.project.demo.User;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("user with ID " + userId + " not found");
    }
}


