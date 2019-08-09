package ru.itpark.planespotting.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthTokenException extends AuthenticationException {
    public AuthTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthTokenException(String msg) {
        super(msg);
    }
}
