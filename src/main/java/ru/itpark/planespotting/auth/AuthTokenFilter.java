package ru.itpark.planespotting.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itpark.planespotting.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final AuthTokenEntryPoint authEntryPoint;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-Token");
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            AuthToken authRequest = new AuthToken(token, null);
            Authentication authResult = tokenService.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        catch(AuthenticationException e) {
            SecurityContextHolder.clearContext();
            authEntryPoint.commence(request, response, e);
            throw e;
        }

        filterChain.doFilter(request, response);
    }
}
