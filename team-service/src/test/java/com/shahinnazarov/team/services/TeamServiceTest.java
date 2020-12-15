package com.shahinnazarov.team.services;

import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.common.utils.impl.ApiResponseGeneratorImpl;
import com.shahinnazarov.team.adapters.PlayerClient;
import com.shahinnazarov.team.container.dto.PlayerResponse;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.repositories.TeamPlayerRepository;
import com.shahinnazarov.team.repositories.TeamRepository;
import com.shahinnazarov.team.services.impl.ContractFeeCalculationServiceImpl;
import com.shahinnazarov.team.services.impl.TeamServiceImpl;
import com.shahinnazarov.team.utils.converters.ApiCollectionConverter;
import com.shahinnazarov.team.utils.converters.TeamEntityConverter;
import com.shahinnazarov.team.utils.converters.TeamResponseConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeamServiceTest {
    private TeamService service;
    private TeamRepository repository;
    private TeamPlayerRepository teamPlayerRepository;
    private PlayerClient client;
    private ApiResponseGenerator responseGenerator;

    @BeforeEach
    public void setUp() {
        repository = mock(TeamRepository.class);
        teamPlayerRepository = mock(TeamPlayerRepository.class);
        client = mock(PlayerClient.class);

        responseGenerator = new ApiResponseGeneratorImpl();
        service = new TeamServiceImpl(
                repository, teamPlayerRepository, client,
                new ContractFeeCalculationServiceImpl(),
                new ApiCollectionConverter(responseGenerator),
                new TeamResponseConverter(),
                new TeamEntityConverter(),
                responseGenerator
        );
    }

    @Test
    void testFetchAll() {
        List<TeamEntity> teamEntities = Collections.singletonList(
                new TeamEntity(1L, "Team A", BigDecimal.TEN, BigDecimal.ONE, Collections.emptyList())
        );
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<TeamEntity> page = new PageImpl(teamEntities, pageRequest, 1);
        when(repository.findAll(pageRequest)).thenReturn(page);
        Collection<TeamResponse> collectionResponse = service.fetchAll(
                pageRequest.getPageNumber(), pageRequest.getPageSize()).getItems();
        Assertions.assertEquals(teamEntities.size(), collectionResponse.size());
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void testFetchAllByPlayerId() {
        Long playerId = 1L;
        List<TeamEntity> teamEntities = Collections.singletonList(
                new TeamEntity(1L, "Team A", BigDecimal.TEN, BigDecimal.ONE, Collections.emptyList())
        );
        when(repository.fetchTeamsByPlayerId(playerId)).thenReturn(teamEntities);
        Collection<TeamResponse> collectionResponse = service.fetchAllByPlayerId(playerId)
                .getItems();
        Assertions.assertEquals(teamEntities.size(), collectionResponse.size());
        verify(repository, times(1)).fetchTeamsByPlayerId(playerId);
    }

    @Test
    void testFetchPlayersByTeamId() {
        Long teamId = 1L;
        PlayerResponse playerResponse = new PlayerResponse(
                1L, "Shahin", "Nazarov", (short) 30, "01/04/1997",
                new BigDecimal("10000")
        );
        when(teamPlayerRepository.fetchPlayerIdsByTeamId(teamId)).thenReturn(
                Collections.singletonList(playerResponse.getId())
        );
        when(client.fetchPlayersByQuery(any())).thenReturn(responseGenerator.generateForCollection(
                Collections.singletonList(playerResponse)
        ));
        Collection<PlayerResponse> collectionResponse = service.fetchPlayersByTeamId(teamId)
                .getItems();
        Assertions.assertEquals(1, collectionResponse.size());
        verify(teamPlayerRepository, times(1)).fetchPlayerIdsByTeamId(teamId);
        verify(client, times(1)).fetchPlayersByQuery(any());
    }

    @Test
    void testFetchById() {
        TeamEntity entity = new TeamEntity(1L, "Team A", BigDecimal.TEN, BigDecimal.ONE,
                Collections.emptyList());
        when(repository.findById(entity.getId())).thenReturn(
                Optional.of(entity)
        );
        TeamResponse teamResponse = service.fetchById(entity.getId()).getItem();
        Assertions.assertEquals(entity.getId(), teamResponse.getId());
        Assertions.assertEquals(entity.getName(), teamResponse.getName());
        Assertions.assertEquals(0, entity.getBudget().compareTo(teamResponse.getBudget()));
        Assertions.assertEquals(0, entity.getCommissionPercentage().compareTo(
                teamResponse.getCommissionPercentage()
        ));
        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    void testSave() {
        TeamRequest request = new TeamRequest(null, "Team A", BigDecimal.ONE);
        when(repository.save(any())).thenAnswer(
                invocation -> {
                    TeamEntity entity = invocation.getArgument(0);
                    entity.setId(1L);
                    return entity;
                }
        );
        TeamResponse teamResponse = service.save(request).getItem();
        Assertions.assertEquals(1L, teamResponse.getId());
        Assertions.assertEquals(request.getName(), teamResponse.getName());
        Assertions.assertEquals(0, BigDecimal.ZERO.compareTo(teamResponse.getBudget()));
        Assertions.assertEquals(0, request.getCommissionPercentage().compareTo(
                teamResponse.getCommissionPercentage()
        ));
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        TeamRequest request = new TeamRequest(1L, "Team A", BigDecimal.ONE);
        when(repository.findById(request.getId())).thenReturn(
                Optional.of(
                        TeamEntity.builder().id(request.getId())
                                .budget(BigDecimal.TEN).build()
                )
        );
        when(repository.save(any())).thenAnswer(
                invocation -> invocation.getArgument(0)
        );
        TeamResponse teamResponse = service.update(request).getItem();
        Assertions.assertEquals(request.getId(), teamResponse.getId());
        Assertions.assertEquals(request.getName(), teamResponse.getName());
        Assertions.assertEquals(0, BigDecimal.TEN.compareTo(teamResponse.getBudget()));
        Assertions.assertEquals(0, request.getCommissionPercentage().compareTo(
                teamResponse.getCommissionPercentage()
        ));
        verify(repository, times(1)).save(any());
    }

    @Test
    void testDelete() {
        Long teamId = 1L;
        when(teamPlayerRepository.deleteAllByTeamId(teamId)).thenReturn(1);
        doNothing().when(repository).deleteById(teamId);
        Long removedId = service.delete(teamId).getItem();
        Assertions.assertEquals(teamId, removedId);
        verify(teamPlayerRepository, times(1)).deleteAllByTeamId(teamId);
        verify(repository, times(1)).deleteById(teamId);
    }

    @Test
    void testCreditTeam() {
        BigDecimal creditedAmount = BigDecimal.ONE;
        BigDecimal budget = BigDecimal.valueOf(100);
        TeamEntity entity = TeamEntity.builder().id(1L).budget(budget).build();
        BigDecimal finalAmount = budget.add(creditedAmount);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(repository.modifyBudget(entity.getId(), finalAmount, budget)).thenReturn(1);
        TeamResponse teamResponse = service.creditTeam(entity.getId(), creditedAmount).getItem();
        Assertions.assertEquals(entity.getId(), teamResponse.getId());
        Assertions.assertEquals(0, finalAmount.compareTo(teamResponse.getBudget()));
        verify(repository, times(1)).findById(entity.getId());
        verify(repository, times(1)).modifyBudget(
                entity.getId(), finalAmount, budget
        );
    }


    @Test
    void testTransferPlayerToTeam() {
        BigDecimal budget = BigDecimal.valueOf(100);
        PlayerResponse playerResponse = new PlayerResponse(
                1L, "Shahin", "Nazarov", (short) 30, "01/04/1997",
                BigDecimal.ONE
        );
        TeamEntity entity = TeamEntity.builder().id(1L)
                .budget(budget)
                .commissionPercentage(BigDecimal.TEN)
                .teams(Collections.emptyList())
                .build();
        BigDecimal finalAmount = budget.subtract(
                playerResponse.getTransferFee().add(
                        playerResponse.getTransferFee().multiply(entity.getCommissionPercentage())
                                .divide(BigDecimal.valueOf(100))
                )
        );
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(client.fetchById(playerResponse.getId())).thenReturn(responseGenerator.generate(playerResponse));
        when(repository.modifyBudget(entity.getId(), finalAmount, budget)).thenReturn(1);
        when(teamPlayerRepository.save(any())).thenAnswer(
                invocation -> invocation.getArgument(0)
        );
        TeamResponse teamResponse = service.transferPlayerToTeam(entity.getId(), playerResponse.getId())
                .getItem();
        Assertions.assertEquals(entity.getId(), teamResponse.getId());
        Assertions.assertEquals(0, finalAmount.compareTo(teamResponse.getBudget()));
        verify(repository, times(1)).findById(entity.getId());
        verify(client, times(1)).fetchById(playerResponse.getId());
        verify(repository, times(1)).modifyBudget(
                entity.getId(), finalAmount, budget
        );
        verify(teamPlayerRepository, times(1)).save(any());
    }

    @Test
    void testRemovePlayerFromTeam() {
        Long playerId = 1L;
        TeamEntity entity = TeamEntity.builder().id(1L)
                .budget(BigDecimal.valueOf(100))
                .commissionPercentage(BigDecimal.TEN)
                .teams(Collections.emptyList())
                .build();
        when(teamPlayerRepository.delete(entity.getId(), playerId)).thenReturn(1);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        TeamResponse teamResponse = service.removePlayerFromTeam(playerId, entity.getId()).getItem();
        Assertions.assertEquals(entity.getId(), teamResponse.getId());
        Assertions.assertEquals(0, entity.getBudget().compareTo(teamResponse.getBudget()));
        verify(teamPlayerRepository, times(1)).delete(entity.getId(), playerId);
        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    void testDeleteByPlayer() {
        Long playerId = 1L;
        when(teamPlayerRepository.deleteAllByPlayerId(playerId)).thenReturn(1);
        Integer response = service.deleteByPlayer(playerId).getItem();
        Assertions.assertEquals(1, response);
        verify(teamPlayerRepository, times(1))
                .deleteAllByPlayerId(playerId);
    }
}
