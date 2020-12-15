package com.shahinnazarov.team.services.impl;

import com.shahinnazarov.common.container.enums.GeneralExceptions;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.team.adapters.PlayerClient;
import com.shahinnazarov.team.container.dto.PlayerQueryRequest;
import com.shahinnazarov.team.container.dto.PlayerResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.container.entities.TeamPlayerEntity;
import com.shahinnazarov.team.container.exceptions.GeneralException;
import com.shahinnazarov.team.repositories.TeamPlayerRepository;
import com.shahinnazarov.team.repositories.TeamRepository;
import com.shahinnazarov.team.services.ContractFeeCalculationService;
import com.shahinnazarov.team.services.TeamService;
import com.shahinnazarov.team.utils.converters.ApiCollectionConverter;
import com.shahinnazarov.team.utils.converters.TeamEntityConverter;
import com.shahinnazarov.team.utils.converters.TeamResponseConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * Implementation of {@link TeamService}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final PlayerClient playerClient;
    private final ContractFeeCalculationService contractFeeCalculationService;
    private final ApiCollectionConverter collectionConverter;
    private final TeamResponseConverter teamResponseConverter;
    private final TeamEntityConverter teamEntityConverter;
    private final ApiResponseGenerator responseGenerator;

    @Override
    public ApiCollectionResponse<TeamResponse> fetchAll(Integer pageIndex, Integer pageSize) {
        log.info("Fetch all players by page(number: {}, size: {}) request called.", pageIndex, pageSize);
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return collectionConverter.convert(
                teamRepository.findAll(pageRequest),
                teamResponseConverter
        );
    }

    @Override
    public ApiCollectionResponse<TeamResponse> fetchAllByPlayerId(Long playerId) {
        log.info("Fetch all teams by playerId ({}) request called.", playerId);
        return responseGenerator.generateForCollection(
                teamRepository.fetchTeamsByPlayerId(playerId).stream()
                        .map(teamResponseConverter::convert).collect(Collectors.toList())
        );
    }

    @Override
    public ApiCollectionResponse<PlayerResponse> fetchPlayersByTeamId(Long teamId) {
        log.info("Fetch all player ids by teamId ({}) request called.", teamId);
        PlayerQueryRequest request = new PlayerQueryRequest();
        request.setIds(teamPlayerRepository.fetchPlayerIdsByTeamId(teamId));
        return playerClient.fetchPlayersByQuery(request);
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
                "Save team request received(name: {}).", request.getName()
        );
        if (!request.validate()) {
            throw GeneralException.of(GeneralExceptions.INVALID_PROPERTY, "commissionPercentage");
        }

        TeamEntity entity = teamEntityConverter.convert(request);
        entity.setBudget(BigDecimal.ZERO);
        entity = teamRepository.save(entity);
        return responseGenerator.generate(teamResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> update(TeamRequest request) {
        log.info(
                "Update team request received(id: {}, name: {}).",
                request.getId(), request.getName()
        );
        TeamEntity oldEntity = teamRepository.findById(request.getId()).orElseThrow(
                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Team")
        );
        TeamEntity entity = teamEntityConverter.convert(request);
        entity.setBudget(oldEntity.getBudget());
        entity = teamRepository.save(entity);
        return responseGenerator.generate(teamResponseConverter.convert(entity));
    }

    @Transactional
    @Override
    public ApiSingleResponse<Long> delete(Long id) {
        log.info("Delete team by id: {} request received.", id);
        teamPlayerRepository.deleteAllByTeamId(id);
        teamRepository.deleteById(id);
        return responseGenerator.generate(id);
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> creditTeam(Long teamId, BigDecimal amount) {
        log.info("Team(id: {}) {} amount credit request called.", teamId, amount);
        TeamEntity team = teamRepository.findById(teamId).orElseThrow(
                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Team")
        );

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw GeneralException.of(GeneralExceptions.MODIFICATION_FAILED_EXCEPTION, "Team.Budget");
        }
        Integer modifyCount = teamRepository.modifyBudget(teamId, team.getBudget().add(amount), team.getBudget());
        if (modifyCount == null || modifyCount <= 0) {
            throw GeneralException.of(GeneralExceptions.MODIFICATION_FAILED_EXCEPTION, "Team.Budget");
        }
        team.setBudget(team.getBudget().add(amount));
        return responseGenerator.generate(teamResponseConverter.convert(team));
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> transferPlayerToTeam(Long playerId, Long teamId) {
        log.info("Transfer player({}) to the team({}) called.", playerId, teamId);
        TeamEntity team = teamRepository.findById(teamId).orElseThrow(
                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Team")
        );

        boolean playerAlreadyTransferred = team.getTeams().stream().anyMatch(
                teamPlayerEntity -> teamPlayerEntity.getPlayerId().equals(playerId)
        );
        if (playerAlreadyTransferred) {
            throw GeneralException.of(
                    GeneralExceptions.OPERATION_ALREADY_EXECUTED, "'transferring the player to the team'"
            );
        }

        ApiSingleResponse<PlayerResponse> playerResponseWrapper = playerClient.fetchById(playerId);
        if (playerResponseWrapper == null || !playerResponseWrapper.getMetadata().isSuccess()) {
            throw GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Player");
        }

        PlayerResponse playerResponse = playerResponseWrapper.getItem();
        BigDecimal contractFee = contractFeeCalculationService.calculate(team, playerResponse.getTransferFee());
        if (contractFee.compareTo(team.getBudget()) > 0) {
            throw GeneralException.of(
                    GeneralExceptions.NOT_ENOUGH_AMOUNT_EXCEPTION,
                    "Team.Budget", contractFee.toString()
            );
        }

        BigDecimal finalAmount = team.getBudget().subtract(contractFee);
        Integer modifyCount = teamRepository.modifyBudget(
                teamId, finalAmount, team.getBudget()
        );
        if (modifyCount == null || modifyCount <= 0) {
            throw GeneralException.of(GeneralExceptions.MODIFICATION_FAILED_EXCEPTION, "Team.Budget");
        }
        TeamPlayerEntity teamPlayerEntity = new TeamPlayerEntity();
        teamPlayerEntity.setPlayerId(playerId);
        teamPlayerEntity.setTeamId(teamId);
        teamPlayerRepository.save(teamPlayerEntity);
        team.setBudget(finalAmount);
        return responseGenerator.generate(teamResponseConverter.convert(team));
    }

    @Transactional
    @Override
    public ApiSingleResponse<TeamResponse> removePlayerFromTeam(Long playerId, Long teamId) {
        log.info("Remove player({}) from the team({}) called.", playerId, teamId);
        Integer modifyCount = teamPlayerRepository.delete(teamId, playerId);
        if (modifyCount == null || modifyCount <= 0) {
            throw GeneralException.of(GeneralExceptions.MODIFICATION_FAILED_EXCEPTION, "Team.Players");
        }
        TeamEntity team = teamRepository.findById(teamId).orElseThrow(
                () -> GeneralException.of(GeneralExceptions.NOT_FOUND_EXCEPTION, "Team")
        );
        return responseGenerator.generate(teamResponseConverter.convert(team));
    }

    @Transactional
    @Override
    public ApiSingleResponse<Integer> deleteByPlayer(Long playerId) {
        log.info("Delete all players from relations player id: {}", playerId);
        return responseGenerator.generate(teamPlayerRepository.deleteAllByPlayerId(playerId));
    }
}
