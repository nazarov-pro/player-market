package com.shahinnazarov.player.services.impl;

import com.shahinnazarov.common.container.enums.GeneralExceptions;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.player.adapters.TeamClient;
import com.shahinnazarov.player.container.dto.PlayerQueryRequest;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.dto.TeamResponse;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.container.exceptions.GeneralException;
import com.shahinnazarov.player.repositories.PlayerRepository;
import com.shahinnazarov.player.services.PlayerService;
import com.shahinnazarov.player.utils.converters.ApiCollectionConverter;
import com.shahinnazarov.player.utils.converters.PlayerEntityConverter;
import com.shahinnazarov.player.utils.converters.PlayerResponseConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Player Service's implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamClient teamClient;
    private final ApiCollectionConverter collectionConverter;
    private final PlayerResponseConverter playerResponseConverter;
    private final PlayerEntityConverter playerEntityConverter;
    private final ApiResponseGenerator responseGenerator;

    @Override
    public ApiCollectionResponse<PlayerResponse> fetchAll(Integer pageIndex, Integer pageSize) {
        log.info("Fetch all players by page(number: {}, size: {}) request called.", pageIndex, pageSize);
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return collectionConverter.convert(
                playerRepository.findAll(pageRequest),
                playerResponseConverter
        );
    }

    @Override
    public ApiCollectionResponse<PlayerResponse> fetchAllByQuery(PlayerQueryRequest request) {
        int countOfPlayers = request.getIds() == null ? 0 : request.getIds().size();
        log.info("Fetch all players by ids(count: {}) request called.", countOfPlayers);
        if (countOfPlayers == 0) {
            return responseGenerator.generateForCollection(Collections.emptyList());
        }

        return responseGenerator.generateForCollection(
                playerRepository.findAllByIdIn(request.getIds())
                        .stream()
                        .map(playerResponseConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ApiCollectionResponse<TeamResponse> fetchTeamsByPlayerId(Long playerId) {
        log.info("Fetch all teams by player id({}) request called.", playerId);
        return teamClient.fetchTeamsByPlayerId(playerId);
    }

    @Override
    public ApiSingleResponse<PlayerResponse> fetchById(Long id) {
        log.info("Find player by id {} called.", id);
        return responseGenerator.generate(
                playerResponseConverter.convert(
                        playerRepository.findById(id).orElseThrow(
                                () -> GeneralException.of(
                                        GeneralExceptions.NOT_FOUND_EXCEPTION, "Player"
                                )
                        )
                )
        );
    }

    @Transactional
    @Override
    public ApiSingleResponse<PlayerResponse> save(PlayerRequest request) {
        log.info(
                "Save player request received(firstname: {}, lastname: {}, experience months: {}).",
                request.getFirstname(), request.getLastname(), request.getMonthsOfExperience()
        );
        PlayerEntity entity = playerRepository.save(playerEntityConverter.convert(request));
        return responseGenerator.generate(playerResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<PlayerResponse> update(PlayerRequest request) {
        log.info(
                "Update player request received(id: {}, firstname: {}, lastname: {}, experience months: {}).",
                request.getId(), request.getFirstname(), request.getLastname(), request.getMonthsOfExperience()
        );
        PlayerEntity entity = playerRepository.save(playerEntityConverter.convert(request));
        return responseGenerator.generate(playerResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<Long> delete(Long id) {
        log.info("Delete player by id: {} request received.", id);
        ApiSingleResponse<Integer> singleResponse = teamClient.deleteByPlayerId(id);
        if (singleResponse == null || !singleResponse.getMetadata().isSuccess()) {
            throw GeneralException.of(GeneralExceptions.REMOTE_SERVICE_CALL_FAILED, "Team Service");
        }
        log.info("{} player relations removed from team service.", singleResponse.getItem());
        playerRepository.deleteById(id);
        return responseGenerator.generate(id);
    }
}
