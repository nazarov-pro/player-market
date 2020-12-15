package com.shahinnazarov.team.services;

import com.shahinnazarov.team.container.entities.TeamEntity;
import com.shahinnazarov.team.services.impl.ContractFeeCalculationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

class ContractFeeCalculationServiceTest {

    @Test
    void testCalculate() {
        BigDecimal playerTransferFee = new BigDecimal("10000");
        TeamEntity entity = new TeamEntity(
                1L, "Team A", new BigDecimal("200000"), BigDecimal.TEN,
                Collections.emptyList()
        );
        ContractFeeCalculationService calculatorService = new ContractFeeCalculationServiceImpl();
        BigDecimal expectedContractFee = playerTransferFee.add(playerTransferFee.multiply(
                entity.getCommissionPercentage().divide(BigDecimal.valueOf(100))
        ));
        BigDecimal contractFee = calculatorService.calculate(entity, playerTransferFee);
        Assertions.assertEquals(0, expectedContractFee.compareTo(contractFee));
    }
}
