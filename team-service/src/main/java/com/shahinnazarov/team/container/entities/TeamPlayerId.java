package com.shahinnazarov.team.container.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"teamId", "playerId"})
public class TeamPlayerId implements Serializable {
    protected Long teamId;
    protected Long playerId;

}
