package com.interswitch.users.exception;

public class UserNotFoundException extends RuntimeException {
    private final String errorCode;

    // Default constructor
    public UserNotFoundException() {
        super("User not found");
        this.errorCode = "USER_NOT_FOUND";
    }

    // Constructor with custom message
    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = "USER_NOT_FOUND";
    }

    // Constructor with custom message and cause
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "USER_NOT_FOUND";
    }

    // Constructor with custom message and error code
    public UserNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
