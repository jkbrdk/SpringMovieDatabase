package cz.itnetwork.entity;

import cz.itnetwork.constant.RoleType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Length(min = 3)
    private String name;

    @Column(nullable = false)
    private Date birthDate;
    private String country;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String biography;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @OneToMany(mappedBy = "director")
    private List<MovieEntity> directedMovies;
    @ManyToMany(mappedBy = "actors")
    private List<MovieEntity> actedInMovies;

}
