package ru.itpark.planespotting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_tokens")
public class AuthTokenEntity {
    @Id
    private String token;
    @ManyToOne(optional = false)
    private UserEntity user;
}
