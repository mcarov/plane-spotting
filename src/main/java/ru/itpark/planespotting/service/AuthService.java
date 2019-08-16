package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.planespotting.dto.AuthRequestDto;
import ru.itpark.planespotting.dto.AuthResponseDto;
import ru.itpark.planespotting.entity.AuthTokenEntity;
import ru.itpark.planespotting.entity.UserEntity;
import ru.itpark.planespotting.repository.AuthTokenRepository;
import ru.itpark.planespotting.repository.UserRepository;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    private final PasswordEncoder encoder;

    public AuthResponseDto authenticate(AuthRequestDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getUsername()));

        if(!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(dto.getUsername());
        }

        if(!user.isEnabled()) {
            throw new DisabledException("api.error.account-disabled");
        }

        String token = UUID.randomUUID().toString();
        AuthTokenEntity authTokenEntity = new AuthTokenEntity(token, user);
        authTokenRepository.save(authTokenEntity);

        String username = user.getUsername();
        String[] authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        return new AuthResponseDto(token, username, authorities);
    }
}
