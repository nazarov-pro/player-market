package com.shahinnazarov.player.services;

import com.shahinnazarov.common.container.BaseService;
import com.shahinnazarov.player.container.entities.PlayerEntity;

import java.math.BigDecimal;

/**
 * Transfer fee calculation service
 */
public interface TransferFeeCalculationService extends BaseService {
    BigDecimal calculate(PlayerEntity playerEntity);

}
