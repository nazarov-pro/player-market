package com.shahinnazarov.player.adapters;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.player.container.dto.TeamResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Team Service's feign client
 */
@FeignClient("team-service")
public interface TeamClient {

    @PatchMapping(
            path = "/teams/players/{playerId}/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ApiSingleResponse<Integer> deleteByPlayerId(
            @PathVariable("playerId") Long playerId
    );

    @GetMapping(
            path = "/teams/search",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ApiCollectionResponse<TeamResponse> fetchTeamsByPlayerId(
            @RequestParam("playerId") Long playerId
    );
}
