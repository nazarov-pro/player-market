package com.shahinnazarov.team.services.impl;

import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.services.ContractFeeCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Implementation of {@link ContractFeeCalculationService}
 */
@Service
public class ContractFeeCalculationServiceImpl implements ContractFeeCalculationService {

    /**
     * Commission calculations
     * ContractFee = Transfer Fee + Team Commission(Transfer Fee * teamCommission%)
     * @param teamEntity - team
     * @param transferFee - amount of transfer fee of the player
     * @return amount of contract fee with commission
     */
    @Override
    public BigDecimal calculate(TeamEntity teamEntity, BigDecimal transferFee) {
        return transferFee.add(transferFee.multiply(teamEntity.getCommissionPercentage())
                .divide(BigDecimal.valueOf(100)));
    }
}
