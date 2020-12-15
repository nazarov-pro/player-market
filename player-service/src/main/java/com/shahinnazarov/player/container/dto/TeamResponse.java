package com.shahinnazarov.player.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Team Response DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamResponse {
    private Long id;
    private String name;
    private BigDecimal budget;
    private BigDecimal commissionPercentage;

}
