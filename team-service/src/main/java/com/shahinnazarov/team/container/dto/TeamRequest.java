package com.shahinnazarov.team.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Team Request DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamRequest {
    private Long id;
    @Size(min = 1, max = 127)
    private String name;
    @Size(max = 10)
    @PositiveOrZero
    private BigDecimal commissionPercentage;

    public boolean validate() {
        return commissionPercentage.compareTo(BigDecimal.ZERO) >= 0 &&
                commissionPercentage.compareTo(BigDecimal.TEN) <= 0;
    }
}
