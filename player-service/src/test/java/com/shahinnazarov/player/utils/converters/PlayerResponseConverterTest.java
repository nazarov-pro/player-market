package com.shahinnazarov.player.utils.converters;


import com.shahinnazarov.common.utils.impl.TimeUtilsImpl;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.services.impl.TransferFeeCalculationServiceImpl;
import com.shahinnazarov.player.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PlayerResponseConverterTest {
    private PlayerResponseConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new PlayerResponseConverter(
                new TransferFeeCalculationServiceImpl(
                        new TimeUtilsImpl()
                )
        );
    }

    @Test
    void testConvert() {
        PlayerEntity entity = new PlayerEntity();
        entity.setId(1L);
        entity.setFirstname("Shahin");
        entity.setLastname("Nazarov");
        entity.setMonthsOfExperience((short) 30);
        entity.setDateOfBirth(LocalDate.of(1997, 04, 01));
        PlayerResponse response = converter.convert(entity);
        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getFirstname(), response.getFirstname());
        Assertions.assertEquals(entity.getLastname(), response.getLastname());
        Assertions.assertEquals(entity.getMonthsOfExperience(), response.getMonthsOfExperience());
        Assertions.assertEquals(entity.getDateOfBirth().format(Constants.DATE_FORMATTER),
                response.getDateOfBirth());
    }
}
