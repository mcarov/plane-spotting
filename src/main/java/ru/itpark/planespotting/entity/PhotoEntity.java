package ru.itpark.planespotting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "photos")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String filename;
    private String photographer;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PlaneEntity plane;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AirlineEntity airline;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AirportEntity airport;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserEntity user;
}
