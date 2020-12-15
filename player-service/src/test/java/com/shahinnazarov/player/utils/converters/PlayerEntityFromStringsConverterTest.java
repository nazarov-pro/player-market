package com.shahinnazarov.player.utils.converters;

import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class PlayerEntityFromStringsConverterTest {
    private PlayerEntityFromStringsConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new PlayerEntityFromStringsConverter();
    }

    @Test
    void testConvert() {
        List<String> strings = Arrays.asList("Lionel", "Messi", "100", "01/02/1987");
        PlayerEntity entity = converter.convert(strings);
        Assertions.assertEquals(strings.get(0), entity.getFirstname());
        Assertions.assertEquals(strings.get(1), entity.getLastname());
        Assertions.assertEquals(Short.parseShort(strings.get(2)), entity.getMonthsOfExperience());
        Assertions.assertEquals(LocalDate.parse(strings.get(3), Constants.DATE_FORMATTER),
                entity.getDateOfBirth());
    }
}
