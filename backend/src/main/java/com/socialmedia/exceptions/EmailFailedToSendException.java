package com.socialmedia.exceptions;

public class EmailFailedToSendException extends RuntimeException {

    private static final long serialVersionUID = 1l;
    public EmailFailedToSendException() {
        super("The email failed to send");
    }
}
