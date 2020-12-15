package com.shahinnazarov.team.services;

import com.shahinnazarov.common.container.BaseService;
import com.shahinnazarov.team.container.entities.TeamEntity;

import java.math.BigDecimal;

/**
 * Contract fee calculations
 */
public interface ContractFeeCalculationService extends BaseService {
    BigDecimal calculate(TeamEntity teamEntity, BigDecimal transferFee);
}
