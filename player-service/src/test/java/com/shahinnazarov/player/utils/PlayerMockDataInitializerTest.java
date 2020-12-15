package com.shahinnazarov.player.utils;

import com.shahinnazarov.player.repositories.PlayerRepository;
import com.shahinnazarov.player.utils.converters.PlayerEntityFromStringsConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PlayerMockDataInitializerTest {
    private PlayerRepository repository;
    private PlayerMockDataInitializer initializer;

    @BeforeEach
    public void setUp() {
        repository = mock(PlayerRepository.class);
        initializer = new PlayerMockDataInitializer(
                repository, new PlayerEntityFromStringsConverter()
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
