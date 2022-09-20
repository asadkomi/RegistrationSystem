package com.socialmedia.exceptions;

public class IncorrectVerificationCodeException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public IncorrectVerificationCodeException() {
        super("The code passed did not match the user verification code");
    }
}
