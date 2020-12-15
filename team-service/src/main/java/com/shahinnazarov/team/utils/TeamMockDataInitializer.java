package com.shahinnazarov.team.utils;

import com.shahinnazarov.common.utils.CsvReader;
import com.shahinnazarov.common.utils.impl.CsvReaderImpl;
import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.repositories.TeamRepository;
import com.shahinnazarov.team.utils.converters.TeamEntityFromStringsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mock data loader if there is not any teams on db
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TeamMockDataInitializer implements ApplicationRunner {
    private final TeamRepository teamRepository;
    private final TeamEntityFromStringsConverter converter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (teamRepository.count() == 0) {
            log.info("Team's mock data will be loaded to db.");
            CsvReader csvReader = new CsvReaderImpl(
                    ";",
                    getClass().getClassLoader().getResourceAsStream(Constants.PATH_TEAM_MOCK_CSV_FILE)
            );
            List<TeamEntity> entities = csvReader.getAll().stream()
                    .skip(1)
                    .filter(converter::validate)
                    .map(converter::convert)
                    .collect(Collectors.toList());
            teamRepository.saveAll(entities);
        }

    }
}
