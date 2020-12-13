package com.shahinnazarov.team.container.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_team_players")
@IdClass(TeamPlayerId.class)
public class TeamPlayerEntity {
    @Id
    @Column(name = "team_id")
    private Long teamId;

    @Id
    @Column(name = "player_id")
    private Long playerId;
}
