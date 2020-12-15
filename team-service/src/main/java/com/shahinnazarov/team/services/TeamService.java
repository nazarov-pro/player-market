package com.shahinnazarov.team.services;

import com.shahinnazarov.common.container.BaseService;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.team.container.dto.PlayerResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;

import java.math.BigDecimal;

/**
 * Team Service (business logic)
 */
public interface TeamService extends BaseService {
    ApiCollectionResponse<TeamResponse> fetchAll(Integer pageIndex, Integer pageSize);

    ApiCollectionResponse<TeamResponse> fetchAllByPlayerId(Long playerId);

    ApiCollectionResponse<PlayerResponse> fetchPlayersByTeamId(Long teamId);

    ApiSingleResponse<TeamResponse> fetchById(Long id);

    ApiSingleResponse<TeamResponse> save(TeamRequest request);

    ApiSingleResponse<TeamResponse> update(TeamRequest request);

    ApiSingleResponse<Long> delete(Long id);

    ApiSingleResponse<TeamResponse> creditTeam(Long teamId, BigDecimal amount);

    ApiSingleResponse<TeamResponse> transferPlayerToTeam(Long playerId, Long teamId);

    ApiSingleResponse<TeamResponse> removePlayerFromTeam(Long playerId, Long teamId);

    ApiSingleResponse<Integer> deleteByPlayer(Long playerId);
}
