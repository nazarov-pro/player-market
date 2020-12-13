package com.shahinnazarov.team.services.impl;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.container.enums.GeneralExceptions;
import com.shahinnazarov.team.container.exceptions.GeneralException;
import com.shahinnazarov.team.repositories.TeamRepository;
import com.shahinnazarov.team.services.TeamService;
import com.shahinnazarov.team.utils.converters.ApiCollectionConverter;
import com.shahinnazarov.team.utils.converters.TeamEntityConverter;
import com.shahinnazarov.team.utils.converters.TeamResponseConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ApiCollectionConverter collectionConverter;
    private final TeamResponseConverter teamResponseConverter;
    private final TeamEntityConverter teamEntityConverter;
    private final ApiResponseGenerator responseGenerator;

    @Override
    public ApiCollectionResponse<TeamResponse> fetchAll(int page, int size) {
        log.info("Fetch all players by page(number: {}, size: {}) request called.", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return collectionConverter.convert(
                teamRepository.findAll(pageRequest),
                teamResponseConverter
        );
    }

    @Override
    public ApiSingleResponse<TeamResponse> fetchById(Long id) {
        log.info("Find player by id {} called.", id);
        return responseGenerator.generate(
                teamResponseConverter.convert(
                        teamRepository.findById(id).orElseThrow(
                                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Team")
                        )
                )
        );
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> save(TeamRequest request) {
        log.info(
                "Save team request received(name: {}).",
                request.getName()
        );
        TeamEntity entity = teamRepository.save(teamEntityConverter.convert(request));
        return responseGenerator.generate(teamResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> update(TeamRequest request) {
        log.info(
                "Update team request received(id: {}, name: {}).",
                request.getId(), request.getName()
        );
        TeamEntity entity = teamRepository.save(teamEntityConverter.convert(request));
        return responseGenerator.generate(teamResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<Long> delete(Long id) {
        log.info("Delete team by id: {} request received.", id);
        teamRepository.deleteById(id);
        return responseGenerator.generate(id);
    }
}
