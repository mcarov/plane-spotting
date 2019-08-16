package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.planespotting.entity.AuthTokenEntity;
import ru.itpark.planespotting.entity.UserEntity;
import ru.itpark.planespotting.exception.AuthTokenException;
import ru.itpark.planespotting.repository.AuthTokenRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService implements AuthenticationManager {
    private final AuthTokenRepository authTokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getPrincipal();

        AuthTokenEntity authTokenEntity = authTokenRepository.findById(token).orElseThrow(() -> new AuthTokenException("api.error.auth-token"));
        UserEntity user = authTokenEntity.getUser();

        return new UsernamePasswordAuthenticationToken(
            user,
            user.getPassword(),
            user.getAuthorities()
        );
    }
}
