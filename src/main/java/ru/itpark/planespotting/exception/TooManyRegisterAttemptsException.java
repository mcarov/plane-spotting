package ru.itpark.planespotting.exception;

public class TooManyRegisterAttemptsException extends RuntimeException {
    public TooManyRegisterAttemptsException() {

    }

    public TooManyRegisterAttemptsException(String message) {
        super(message);
    }
}
