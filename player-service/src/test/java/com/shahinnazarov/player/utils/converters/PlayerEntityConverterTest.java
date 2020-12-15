package com.shahinnazarov.player.utils.converters;


import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PlayerEntityConverterTest {
    private PlayerEntityConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new PlayerEntityConverter();
    }

    @Test
    void testConvert() {
        PlayerRequest request = new PlayerRequest();
        request.setId(1L);
        request.setFirstname("Shahin");
        request.setLastname("Nazarov");
        request.setMonthsOfExperience((short) 30);
        request.setDateOfBirth("01/04/1997");
        PlayerEntity entity = converter.convert(request);
        Assertions.assertEquals(request.getId(), entity.getId());
        Assertions.assertEquals(request.getFirstname(), entity.getFirstname());
        Assertions.assertEquals(request.getLastname(), entity.getLastname());
        Assertions.assertEquals(request.getMonthsOfExperience(), entity.getMonthsOfExperience());
        Assertions.assertEquals(LocalDate.parse(request.getDateOfBirth(), Constants.DATE_FORMATTER),
                entity.getDateOfBirth());
    }
}
