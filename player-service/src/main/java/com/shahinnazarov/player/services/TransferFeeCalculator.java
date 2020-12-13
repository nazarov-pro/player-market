package com.shahinnazarov.player.services;

import com.shahinnazarov.player.container.entities.PlayerEntity;

import java.math.BigDecimal;

public interface TransferFeeCalculator {
    BigDecimal calculateTransferFee(PlayerEntity playerEntity);

}
