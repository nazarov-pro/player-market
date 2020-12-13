package com.shahinnazarov.player.resources;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerResource {
    private final PlayerService playerService;

    @GetMapping
    public Mono<ResponseEntity<ApiCollectionResponse<PlayerResponse>>> getPage(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchAll(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiSingleResponse<PlayerResponse>>> getById(
            @PathVariable("id") Long id
    ) {
        return Mono.just(ResponseEntity.ok(playerService.fetchById(id)));
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
