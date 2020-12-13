package com.shahinnazarov.player.services;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;

public interface PlayerService {
    ApiCollectionResponse<PlayerResponse> fetchAll(int startIndex, int offset);
    ApiSingleResponse<PlayerResponse> fetchById(Long id);
    ApiSingleResponse<PlayerResponse> save(PlayerRequest request);
    ApiSingleResponse<PlayerResponse> update(PlayerRequest request);
    ApiSingleResponse<Long> delete(Long id);
}
