package com.shahinnazarov.team.adapters;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.team.container.dto.PlayerQueryRequest;
import com.shahinnazarov.team.container.dto.PlayerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client of player service
 */
@FeignClient(name = "player-service")
public interface PlayerClient {
    @GetMapping(
            path = "/players/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ApiSingleResponse<PlayerResponse> fetchById(@PathVariable("id") Long playerId);

    @PostMapping(
            path = "/players/query",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ApiCollectionResponse<PlayerResponse> fetchPlayersByQuery(@RequestBody PlayerQueryRequest request);

}
