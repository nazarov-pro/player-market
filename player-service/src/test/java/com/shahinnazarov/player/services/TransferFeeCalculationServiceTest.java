package com.shahinnazarov.player.services;

import com.shahinnazarov.common.utils.TimeUtils;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.services.impl.TransferFeeCalculationServiceImpl;
import com.shahinnazarov.player.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransferFeeCalculationServiceTest {
    private TransferFeeCalculationService calculatorService;
    private TimeUtils timeUtils;

    @BeforeEach
    public void setUp() {
        timeUtils = mock(TimeUtils.class);
        calculatorService = new TransferFeeCalculationServiceImpl(timeUtils);
    }

    @Test
    void testCalculate() {
        int age = 25;
        LocalDate birthdayOfPlayer = LocalDate.of(1997, 04, 01);
        when(timeUtils.nowDateAsUTC()).thenReturn(birthdayOfPlayer.plusYears(age));

        PlayerEntity entity = new PlayerEntity();
        entity.setDateOfBirth(birthdayOfPlayer);
        entity.setMonthsOfExperience((short) 10);
        BigDecimal expectedTransferFee = Constants.MONTHLY_EXPERIENCE_AMOUNT
                .multiply(BigDecimal.valueOf(entity.getMonthsOfExperience()))
                .divide(BigDecimal.valueOf(age), 2, RoundingMode.FLOOR);

        BigDecimal transferFee = calculatorService.calculate(entity);
        Assertions.assertEquals(expectedTransferFee, transferFee);
    }
}
