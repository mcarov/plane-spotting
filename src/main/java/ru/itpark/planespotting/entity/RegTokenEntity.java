package ru.itpark.planespotting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reg_tokens")
public class RegTokenEntity {
    @Id
    private String token;
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity user;
    private LocalDateTime created;
}
