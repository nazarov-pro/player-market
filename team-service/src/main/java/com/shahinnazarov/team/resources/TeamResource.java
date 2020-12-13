package com.shahinnazarov.team.resources;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamResource {
    private final TeamService teamService;

    @GetMapping
    public Mono<ResponseEntity<ApiCollectionResponse<TeamResponse>>> getPage(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return Mono.just(ResponseEntity.ok(teamService.fetchAll(pageNumber, pageSize)));
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
}
