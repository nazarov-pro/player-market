package com.shahinnazarov.player.services.impl;

import com.shahinnazarov.common.utils.TimeUtils;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.services.TransferFeeCalculator;
import com.shahinnazarov.player.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferFeeCalculatorImpl implements TransferFeeCalculator {
    private final TimeUtils timeUtils;

    @Override
    public BigDecimal calculateTransferFee(PlayerEntity playerEntity) {
        LocalDate now = timeUtils.nowDateAsUTC();
        int age = now.getYear() - playerEntity.getDateOfBirth().getYear();
        if(now.isBefore(playerEntity.getDateOfBirth())) {
            age--;
        }

        return BigDecimal.valueOf(playerEntity.getMonthsOfExperience())
                .multiply(Constants.MONTHLY_EXPERIENCE_AMOUNT)
                .divide(BigDecimal.valueOf(age), 2,RoundingMode.FLOOR);
    }
}
