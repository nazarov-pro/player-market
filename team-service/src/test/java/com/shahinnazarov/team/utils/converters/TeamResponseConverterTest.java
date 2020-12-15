package com.shahinnazarov.team.utils.converters;


import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.container.entities.TeamEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

class TeamResponseConverterTest {
    private TeamResponseConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new TeamResponseConverter();
    }

    @Test
    void testConvert() {
        TeamEntity entity = new TeamEntity(
                1L, "Team A", BigDecimal.TEN, BigDecimal.ONE,
                Collections.emptyList()
        );

        TeamResponse response = converter.convert(entity);
        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getName(), response.getName());
        Assertions.assertEquals(entity.getBudget(), response.getBudget());
        Assertions.assertEquals(entity.getCommissionPercentage(),
                response.getCommissionPercentage());
    }
}
