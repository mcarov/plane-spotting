package ru.itpark.planespotting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.planespotting.entity.PhotoEntity;
import ru.itpark.planespotting.entity.UserEntity;
import ru.itpark.planespotting.repository.PhotoRepository;
import ru.itpark.planespotting.repository.UserRepository;
import ru.itpark.planespotting.service.CsvFileService;

import java.util.List;

@SpringBootApplication
public class PlaneSpottingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaneSpottingApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(CsvFileService service,
                                    PhotoRepository photoRepository,
                                    UserRepository userRepository,
                                    PasswordEncoder encoder) {
        return args -> {
            UserEntity user = new UserEntity();
            user.setId(1);
            user.setUsername("mcarov");
            user.setEmail("mcarov@localhost.ru");
            user.setPassword(encoder.encode("psw_85"));
            user.setCountry("Russian Federation");
            user.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER"),
                                        new SimpleGrantedAuthority("ROLE_ADMIN")));
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            userRepository.save(user);

            List<PhotoEntity> entities = service.importFromCsvFile();
            entities.forEach(e -> {
                e.setUser(user);
                photoRepository.save(e);
            });

        };
    }
}
