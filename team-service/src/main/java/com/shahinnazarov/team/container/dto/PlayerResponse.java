package com.shahinnazarov.team.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Player Response DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Short monthsOfExperience;
    private String dateOfBirth;
    private BigDecimal transferFee;

}
