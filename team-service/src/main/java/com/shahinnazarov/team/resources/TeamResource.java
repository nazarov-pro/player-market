package com.shahinnazarov.team.resources;

import com.shahinnazarov.common.container.BaseResource;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.team.container.dto.PlayerResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Team Resource REST API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamResource implements BaseResource {
    private final TeamService teamService;

    @GetMapping
    public Mono<ResponseEntity<ApiCollectionResponse<TeamResponse>>> getPage(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return Mono.just(ResponseEntity.ok(teamService.fetchAll(pageIndex, pageSize)));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<ApiCollectionResponse<TeamResponse>>> getTeamsByPlayerId(
            @RequestParam("playerId") Long playerId
    ) {
        return Mono.just(ResponseEntity.ok(teamService.fetchAllByPlayerId(playerId)));
    }

    @GetMapping("/{id}/players")
    public Mono<ResponseEntity<ApiCollectionResponse<PlayerResponse>>> getPlayersOfTeam(
            @PathVariable("id") Long teamId
    ) {
        return Mono.just(ResponseEntity.ok(teamService.fetchPlayersByTeamId(teamId)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> getById(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(teamService.fetchById(id)));
    }

    @PostMapping
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> save(
            @Valid @RequestBody TeamRequest request
    ) {
        return Mono.just(ResponseEntity.ok(teamService.save(request)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> update(
            @Valid @RequestBody TeamRequest request,
            @PathVariable("id") Long id
    ) {
        request.setId(id);
        return Mono.just(ResponseEntity.ok(teamService.update(request)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<Long>>> delete(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(teamService.delete(id)));
    }

    @PatchMapping("/players/{playerId}/remove")
    public Mono<ResponseEntity<ApiSingleResponse<Integer>>> deleteByPlayerId(
            @PathVariable("playerId") Long playerId
    ) {
        return Mono.just(ResponseEntity.ok(teamService.deleteByPlayer(playerId)));
    }

    @PatchMapping("/{id}/credit")
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> credit(
            @PathVariable("id") Long id,
            @Positive @RequestParam("amount") BigDecimal amount
    ) {
        return Mono.just(ResponseEntity.ok(teamService.creditTeam(id, amount)));
    }

    @PatchMapping("/{teamId}/players/{playerId}/transfer")
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> transferPlayer(
            @PathVariable("teamId") Long teamId,
            @PathVariable("playerId") Long playerId
    ) {
        return Mono.just(ResponseEntity.ok(teamService.transferPlayerToTeam(playerId, teamId)));
    }

    @PatchMapping("/{teamId}/players/{playerId}/remove")
    public Mono<ResponseEntity<ApiSingleResponse<TeamResponse>>> removePlayer(
            @PathVariable("teamId") Long teamId,
            @PathVariable("playerId") Long playerId
    ) {
        return Mono.just(ResponseEntity.ok(teamService.removePlayerFromTeam(playerId, teamId)));
    }
}
