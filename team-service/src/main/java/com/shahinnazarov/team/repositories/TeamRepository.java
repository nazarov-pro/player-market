package com.shahinnazarov.team.repositories;

import com.shahinnazarov.team.container.BaseRepository;
import com.shahinnazarov.team.container.entities.TeamEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Team Repository
 */
public interface TeamRepository extends BaseRepository<TeamEntity, Long> {

    @Modifying
    @Query("update TeamEntity set budget=:newBudget where id=:teamId and budget=:oldBudget")
    Integer modifyBudget(
            @Param("teamId") Long teamId,
            @Param("newBudget") BigDecimal newBudget,
            @Param("oldBudget") BigDecimal oldBudget
    );

    @Query("from TeamEntity te join te.teams tr where tr.playerId=:playerId")
    List<TeamEntity> fetchTeamsByPlayerId(@Param("playerId") Long playerId);
}
