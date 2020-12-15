package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.team.container.entities.TeamEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class TeamEntityFromStringsConverterTest {
    private TeamEntityFromStringsConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new TeamEntityFromStringsConverter();
    }

    @Test
    void testConvert() {
        List<String> strings = Arrays.asList("Team A", "1000000", "7.56");
        TeamEntity entity = converter.convert(strings);
        Assertions.assertEquals(strings.get(0), entity.getName());
        Assertions.assertEquals(
                0, entity.getBudget()
                        .compareTo(new BigDecimal(strings.get(1)))
        );
        Assertions.assertEquals(
                0, entity.getCommissionPercentage()
                        .compareTo(new BigDecimal(strings.get(2)))
        );
    }
}
