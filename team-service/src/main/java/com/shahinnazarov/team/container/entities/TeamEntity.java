package com.shahinnazarov.team.container.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Team Entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_team")
public class TeamEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_team_id_seq_generator"
    )
    @SequenceGenerator(
            name = "t_team_id_seq_generator",
            sequenceName = "t_team_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 127)
    private String name;
    @Column(name = "budget")
    private BigDecimal budget;
    @Column(name = "commission_percentage")
    private BigDecimal commissionPercentage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teamId")
    private List<TeamPlayerEntity> teams;
}
