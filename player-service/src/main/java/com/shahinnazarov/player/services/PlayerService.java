package com.shahinnazarov.player.services;

import com.shahinnazarov.common.container.BaseService;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.player.container.dto.PlayerQueryRequest;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.dto.TeamResponse;

/**
 * Player Service (Business logic)
 */
public interface PlayerService extends BaseService {
    ApiCollectionResponse<PlayerResponse> fetchAll(Integer pageIndex, Integer pageSize);

    ApiCollectionResponse<PlayerResponse> fetchAllByQuery(PlayerQueryRequest request);

    ApiCollectionResponse<TeamResponse> fetchTeamsByPlayerId(Long playerId);

    ApiSingleResponse<PlayerResponse> fetchById(Long id);

    ApiSingleResponse<PlayerResponse> save(PlayerRequest request);

    ApiSingleResponse<PlayerResponse> update(PlayerRequest request);

    ApiSingleResponse<Long> delete(Long id);
}
