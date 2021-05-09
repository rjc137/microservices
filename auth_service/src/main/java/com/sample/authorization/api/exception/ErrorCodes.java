package com.sample.authorization.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    USER_NOT_FOUND(302, "User not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error has occured"), 
    INVALID_LOGIN(305, "Invalid login"), 
    USER_EXISTS(306, "User Already Exists"), 
    ROLE_NOT_FOUND(307, "Role Not Found"), 
    USER_CREATION_ERROR(308, "Error Creating User");

    private int id;
    private String description;
   
}
