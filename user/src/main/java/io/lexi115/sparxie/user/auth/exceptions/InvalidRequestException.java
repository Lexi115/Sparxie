package io.lexi115.sparxie.user.auth.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Request is invalid!");
    }
}
