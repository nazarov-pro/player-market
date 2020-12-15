package com.shahinnazarov.player.utils;

import com.shahinnazarov.common.utils.CsvReader;
import com.shahinnazarov.common.utils.impl.CsvReaderImpl;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.repositories.PlayerRepository;
import com.shahinnazarov.player.utils.converters.PlayerEntityFromStringsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mock data initializer if there is not any players.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerMockDataInitializer implements ApplicationRunner {
    private final PlayerRepository playerRepository;
    private final PlayerEntityFromStringsConverter converter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (playerRepository.count() == 0) {
            log.info("Player's mock data will be loaded to db.");
            CsvReader csvReader = new CsvReaderImpl(
                    ";",
                    getClass().getClassLoader().getResourceAsStream(Constants.PATH_PLAYER_MOCK_CSV_FILE)
            );
            List<PlayerEntity> entities = csvReader.getAll().stream()
                    .skip(1)
                    .filter(converter::validate)
                    .map(converter::convert)
                    .collect(Collectors.toList());
            playerRepository.saveAll(entities);
        }
    }
}
