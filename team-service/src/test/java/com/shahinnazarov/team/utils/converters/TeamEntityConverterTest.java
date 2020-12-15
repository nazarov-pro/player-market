package com.shahinnazarov.team.utils.converters;


import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.entities.TeamEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TeamEntityConverterTest {
    private TeamEntityConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new TeamEntityConverter();
    }

    @Test
    void testConvert() {
        TeamRequest request = new TeamRequest(
                1L, "Team A", BigDecimal.ONE
        );
        TeamEntity entity = converter.convert(request);
        Assertions.assertEquals(request.getId(), entity.getId());
        Assertions.assertEquals(request.getName(), entity.getName());
        Assertions.assertEquals(request.getCommissionPercentage(),
                entity.getCommissionPercentage());
    }
}
