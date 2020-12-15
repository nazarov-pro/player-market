package com.shahinnazarov.player.resources;

import com.shahinnazarov.common.container.BaseResource;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.player.container.dto.PlayerQueryRequest;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.dto.TeamResponse;
import com.shahinnazarov.player.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Player Resource REST API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerResource implements BaseResource {
    private final PlayerService playerService;

    @GetMapping
    public Mono<ResponseEntity<ApiCollectionResponse<PlayerResponse>>> getPage(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchAll(pageIndex, pageSize)));
    }

    @PostMapping("/query")
    public Mono<ResponseEntity<ApiCollectionResponse<PlayerResponse>>> getPlayers(
            @Valid @RequestBody PlayerQueryRequest request
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchAllByQuery(request)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<PlayerResponse>>> getById(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchById(id)));
    }

    @GetMapping("/{id}/teams")
    public Mono<ResponseEntity<ApiCollectionResponse<TeamResponse>>> getTeamsByPlayerId(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchTeamsByPlayerId(id)));
    }

    @PostMapping
    public Mono<ResponseEntity<ApiSingleResponse<PlayerResponse>>> save(
            @Valid @RequestBody PlayerRequest request
    ) {
        return Mono.just(ResponseEntity.ok(playerService.save(request)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<PlayerResponse>>> update(
            @Valid @RequestBody PlayerRequest request,
            @PathVariable("id") Long id
    ) {
        request.setId(id);
        return Mono.just(ResponseEntity.ok(playerService.update(request)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<Long>>> delete(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(playerService.delete(id)));
    }
}
