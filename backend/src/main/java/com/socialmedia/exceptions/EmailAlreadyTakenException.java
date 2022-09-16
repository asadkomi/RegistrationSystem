package com.socialmedia.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public EmailAlreadyTakenException() {
        super("The email provided is already taken");
    }
}
