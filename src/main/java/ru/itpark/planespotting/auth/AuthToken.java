package ru.itpark.planespotting.auth;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class AuthToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final Object credentials;

    public AuthToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
    }
}
