package com.shahinnazarov.team.utils;

import com.shahinnazarov.team.repositories.TeamRepository;
import com.shahinnazarov.team.utils.converters.TeamEntityFromStringsConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TeamMockDataInitializerTest {
    private TeamRepository repository;
    private TeamMockDataInitializer initializer;

    @BeforeEach
    public void setUp() {
        repository = mock(TeamRepository.class);
        initializer = new TeamMockDataInitializer(
                repository, new TeamEntityFromStringsConverter()
        );
    }

    @Test
    void givenZeroPlayersWhenPlayersEmptyThenInitialize() throws Exception {
        when(repository.count()).thenReturn(0L);
        initializer.run(null);
        verify(repository).saveAll(any());
    }

    @Test
    void givenAnyPlayersWhenPlayersNotEmptyThenSkipInitialization() throws Exception {
        when(repository.count()).thenReturn(3L);
        initializer.run(null);
        verify(repository, times(0)).saveAll(any());
    }

}
