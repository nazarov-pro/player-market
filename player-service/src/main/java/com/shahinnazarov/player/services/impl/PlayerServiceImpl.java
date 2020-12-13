package com.shahinnazarov.player.services.impl;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.container.enums.GeneralExceptions;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ApiCollectionConverter collectionConverter;
    private final PlayerResponseConverter playerResponseConverter;
    private final PlayerEntityConverter playerEntityConverter;
    private final ApiResponseGenerator responseGenerator;

    @Override
    public ApiCollectionResponse<PlayerResponse> fetchAll(int page, int size) {
        log.info("Fetch all players by page(number: {}, size: {}) request called.", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return collectionConverter.convert(
                playerRepository.findAll(pageRequest),
                playerResponseConverter
        );
    }

    @Override
    public ApiSingleResponse<PlayerResponse> fetchById(Long id) {
        log.info("Find player by id {} called.", id);
        return responseGenerator.generate(
                playerResponseConverter.convert(
                        playerRepository.findById(id).orElseThrow(
                                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Player")
                        )
                )
        );
    }

    @Transactional
    @Override
    public ApiSingleResponse<PlayerResponse> save(PlayerRequest request) {
        log.info(
                "Save player request received(firstname: {}, lastname: {}, experience months: {}).",
                request.getFirstname(), request.getLastname(), request.getMonthOfExperience()
        );
        PlayerEntity entity = playerRepository.save(playerEntityConverter.convert(request));
        return responseGenerator.generate(playerResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<PlayerResponse> update(PlayerRequest request) {
        log.info(
                "Update player request received(id: {}, firstname: {}, lastname: {}, experience months: {}).",
                request.getId(), request.getFirstname(), request.getLastname(), request.getMonthOfExperience()
        );
        PlayerEntity entity = playerRepository.save(playerEntityConverter.convert(request));
        return responseGenerator.generate(playerResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<Long> delete(Long id) {
        log.info("Delete player by id: {} request received.", id);
        playerRepository.deleteById(id);
        return responseGenerator.generate(id);
    }
}
