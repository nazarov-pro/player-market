package com.shahinnazarov.team.repositories;

import com.shahinnazarov.team.container.BaseRepository;
import com.shahinnazarov.team.container.entities.TeamPlayerEntity;
import com.shahinnazarov.team.container.entities.TeamPlayerId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Team Player Repository
 */
public interface TeamPlayerRepository extends BaseRepository<TeamPlayerEntity, TeamPlayerId> {
    @Modifying
    @Query("delete from TeamPlayerEntity tp where tp.playerId=:playerId")
    Integer deleteAllByPlayerId(@Param("playerId") Long playerId);

    @Modifying
    @Query("delete from TeamPlayerEntity tp where tp.teamId=:teamId")
    Integer deleteAllByTeamId(@Param("teamId") Long teamId);

    @Modifying
    @Query("delete from TeamPlayerEntity tp where tp.teamId=:teamId and tp.playerId=:playerId")
    Integer delete(@Param("teamId") Long teamId, @Param("playerId") Long playerId);

    @Query("select tp.playerId from TeamPlayerEntity tp where tp.teamId=:teamId")
    Collection<Long> fetchPlayerIdsByTeamId(@Param("teamId") Long teamId);
}
