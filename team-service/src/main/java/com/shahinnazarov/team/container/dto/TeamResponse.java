package com.shahinnazarov.team.container.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeamResponse {
    private Long id;
    private String name;
    private BigDecimal budget;

}
