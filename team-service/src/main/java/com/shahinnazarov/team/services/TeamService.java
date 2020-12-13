package com.shahinnazarov.team.services;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;

public interface TeamService {
    ApiCollectionResponse<TeamResponse> fetchAll(int startIndex, int offset);
    ApiSingleResponse<TeamResponse> fetchById(Long id);
    ApiSingleResponse<TeamResponse> save(TeamRequest request);
    ApiSingleResponse<TeamResponse> update(TeamRequest request);
    ApiSingleResponse<Long> delete(Long id);
}
