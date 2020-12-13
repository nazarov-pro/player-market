package com.shahinnazarov.player.container.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "p_player")
public class PlayerEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "p_player_id_seq_generator"
    )
    @SequenceGenerator(
            name = "p_player_id_seq_generator",
            sequenceName = "p_player_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname", length = 63)
    private String firstname;
    @Column(name = "lastname", length = 63)
    private String lastname;
    @Column(name = "experience_months")
    private Short monthsOfExperience;
    @Column(name = "birthday")
    private LocalDate dateOfBirth;

}
