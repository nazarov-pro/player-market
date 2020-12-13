package com.shahinnazarov.player.container.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlayerResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Short monthsOfExperience;
    private String dateOfBirth;
    private BigDecimal transferFee;

}
