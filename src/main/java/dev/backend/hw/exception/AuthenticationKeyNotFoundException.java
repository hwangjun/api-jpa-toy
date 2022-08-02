package dev.backend.hw.exception;

public class AuthenticationKeyNotFoundException extends RuntimeException {

    public AuthenticationKeyNotFoundException() {
        super();
    }

    public AuthenticationKeyNotFoundException(String message) {
        super(message);
    }
}
