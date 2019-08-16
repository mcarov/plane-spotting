package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.planespotting.dto.RegConfirmRequestDto;
import ru.itpark.planespotting.dto.RegRequestDto;
import ru.itpark.planespotting.entity.RegTokenEntity;
import ru.itpark.planespotting.entity.UserEntity;
import ru.itpark.planespotting.exception.RegTokenException;
import ru.itpark.planespotting.exception.TooManyRegisterAttemptsException;
import ru.itpark.planespotting.exception.UsernameAlreadyExistsException;
import ru.itpark.planespotting.repository.AuthTokenRepository;
import ru.itpark.planespotting.repository.RegTokenRepository;
import ru.itpark.planespotting.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegService {
    private final UserRepository userRepository;
    private final RegTokenRepository regTokenRepository;
    private final AuthTokenRepository authTokenRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    public void register(RegRequestDto dto) {
        Optional<UserEntity> user = userRepository.findByUsername(dto.getUsername());

        if(user.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(dto.getUsername());
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(encoder.encode(dto.getPassword()));
            newUser.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER")));
            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setCredentialsNonExpired(true);
            newUser.setEnabled(false);
            userRepository.save(newUser);

            RegTokenEntity regToken = new RegTokenEntity();
            regToken.setToken(UUID.randomUUID().toString());
            regToken.setUser(newUser);
            regToken.setCreated(LocalDateTime.now());
            regTokenRepository.save(regToken);

            String registerLink = regToken.getToken();
        }
        else {
            if(user.get().isEnabled()) {
                throw new UsernameAlreadyExistsException(dto.getUsername());
            }

            int count = regTokenRepository.countRegTokensByUserId(user.get().getId());
            if(count >= 3) {
                throw new TooManyRegisterAttemptsException("api.error.many-attempts");
            }

            RegTokenEntity regToken = new RegTokenEntity();
            regToken.setToken(UUID.randomUUID().toString());
            regToken.setUser(user.get());
            regToken.setCreated(LocalDateTime.now());
            regTokenRepository.save(regToken);
        }
    }

    public void confirm(RegConfirmRequestDto dto) {
        RegTokenEntity token = regTokenRepository.findById(dto.getToken()).orElseThrow(() -> new RegTokenException("api.error.reg-token"));
        UserEntity user = token.getUser();
        user.setEnabled(true);
        regTokenRepository.delete(token);
    }
}
