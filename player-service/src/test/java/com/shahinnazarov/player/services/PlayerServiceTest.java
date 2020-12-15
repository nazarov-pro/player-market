package com.shahinnazarov.player.services;

import com.shahinnazarov.common.utils.impl.ApiResponseGeneratorImpl;
import com.shahinnazarov.player.adapters.TeamClient;
import com.shahinnazarov.player.container.dto.PlayerQueryRequest;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.dto.TeamResponse;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.repositories.PlayerRepository;
import com.shahinnazarov.player.services.impl.PlayerServiceImpl;
import com.shahinnazarov.player.utils.Constants;
import com.shahinnazarov.player.utils.converters.ApiCollectionConverter;
import com.shahinnazarov.player.utils.converters.PlayerEntityConverter;
import com.shahinnazarov.player.utils.converters.PlayerResponseConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

class PlayerServiceTest {
    private PlayerService service;
    private PlayerRepository repository;
    private TeamClient client;
    private TransferFeeCalculationService calculatorService;

    @BeforeEach
    public void setUp() {
        repository = mock(PlayerRepository.class);
        client = mock(TeamClient.class);
        calculatorService = mock(TransferFeeCalculationService.class);

        service = new PlayerServiceImpl(
                repository, client,
                new ApiCollectionConverter(new ApiResponseGeneratorImpl()),
                new PlayerResponseConverter(calculatorService),
                new PlayerEntityConverter(),
                new ApiResponseGeneratorImpl()
        );
    }

    @Test
    void testFetchAll() {
        List<PlayerEntity> playerEntities = Collections.singletonList(
                new PlayerEntity(1L, "Shahin", "Nazarov", (short) 3,
                        LocalDate.of(1997, 04, 01))
        );
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<PlayerEntity> page = new PageImpl(playerEntities, pageRequest, 1);
        when(repository.findAll(pageRequest)).thenReturn(page);
        when(calculatorService.calculate(any())).thenReturn(BigDecimal.ONE);
        Collection<PlayerResponse> collectionResponse = service.fetchAll(
                pageRequest.getPageNumber(), pageRequest.getPageSize()).getItems();
        Assertions.assertEquals(playerEntities.size(), collectionResponse.size());
        verify(calculatorService, times(1)).calculate(playerEntities.get(0));
    }

    @Test
    void testFetchAllByQuery() {
        List<PlayerEntity> playerEntities = Collections.singletonList(
                new PlayerEntity(1L, "Shahin", "Nazarov", (short) 3,
                        LocalDate.of(1997, 04, 01))
        );
        PlayerQueryRequest request = new PlayerQueryRequest(Arrays.asList(1L, 2L, 3L));
        when(repository.findAllByIdIn(request.getIds())).thenReturn(playerEntities);
        when(calculatorService.calculate(any())).thenReturn(BigDecimal.ONE);
        Collection<PlayerResponse> collectionResponse = service.fetchAllByQuery(request).getItems();
        Assertions.assertEquals(playerEntities.size(), collectionResponse.size());
        verify(calculatorService, times(1)).calculate(playerEntities.get(0));
    }

    @Test
    void testFetchTeamsByPlayerId() {
        Long playerId = 1L;
        when(client.fetchTeamsByPlayerId(playerId)).thenReturn(
                new ApiResponseGeneratorImpl().generateForCollection(
                        Collections.singleton(
                                new TeamResponse(1L, "Team A", BigDecimal.TEN, BigDecimal.ONE)
                        )
                )
        );
        service.fetchTeamsByPlayerId(playerId);
        verify(client, times(1)).fetchTeamsByPlayerId(playerId);
    }

    @Test
    void testFetchById() {
        PlayerEntity entity = new PlayerEntity(1L, "Shahin", "Nazarov", (short) 3,
                LocalDate.of(1997, 04, 01));
        when(repository.findById(entity.getId())).thenReturn(
                Optional.of(entity)
        );
        when(calculatorService.calculate(any())).thenReturn(BigDecimal.ONE);
        PlayerResponse playerResponse = service.fetchById(entity.getId()).getItem();
        Assertions.assertEquals(entity.getId(), playerResponse.getId());
        Assertions.assertEquals(entity.getFirstname(), playerResponse.getFirstname());
        Assertions.assertEquals(entity.getLastname(), playerResponse.getLastname());
        Assertions.assertEquals(entity.getMonthsOfExperience(), playerResponse.getMonthsOfExperience());
        Assertions.assertEquals(entity.getDateOfBirth().format(Constants.DATE_FORMATTER),
                playerResponse.getDateOfBirth());
        verify(calculatorService, times(1)).calculate(entity);
    }

    @Test
    void testSave() {
        PlayerRequest request = new PlayerRequest(1L, "Shahin", "Nazarov",
                (short) 3, "01/04/1997");
        when(repository.save(any())).thenAnswer(
                invocation -> {
                    PlayerEntity entity = invocation.getArgument(0);
                    entity.setId(request.getId());
                    return entity;
                }
        );
        when(calculatorService.calculate(any())).thenReturn(BigDecimal.ONE);
        PlayerResponse playerResponse = service.save(request).getItem();
        Assertions.assertEquals(request.getId(), playerResponse.getId());
        Assertions.assertEquals(request.getDateOfBirth(), playerResponse.getDateOfBirth());
        verify(calculatorService, times(1)).calculate(any());
    }

    @Test
    void testUpdate() {
        PlayerRequest request = new PlayerRequest(1L, "Shahin", "Nazarov",
                (short) 3, "01/04/1997");
        when(repository.save(any())).thenAnswer(
                invocation -> {
                    PlayerEntity entity = invocation.getArgument(0);
                    entity.setId(request.getId());
                    return entity;
                }
        );
        when(calculatorService.calculate(any())).thenReturn(BigDecimal.ONE);
        PlayerResponse playerResponse = service.update(request).getItem();
        Assertions.assertEquals(request.getId(), playerResponse.getId());
        Assertions.assertEquals(request.getDateOfBirth(), playerResponse.getDateOfBirth());
        verify(calculatorService, times(1)).calculate(any());
    }

    @Test
    void testDelete() {
        Long playerId = 1L;
        when(client.deleteByPlayerId(playerId)).thenReturn(
                new ApiResponseGeneratorImpl().generate(1)
        );
        doNothing().when(repository).deleteById(playerId);
        service.delete(playerId);
        verify(client, times(1)).deleteByPlayerId(playerId);
        verify(repository, times(1)).deleteById(playerId);
    }
}
