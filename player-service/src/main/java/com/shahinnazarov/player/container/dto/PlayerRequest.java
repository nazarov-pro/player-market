package com.shahinnazarov.player.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Player Request DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerRequest {
    private Long id;
    @Size(min = 1, max = 63)
    private String firstname;
    @Size(min = 1, max = 63)
    private String lastname;
    @PositiveOrZero
    private Short monthsOfExperience;
    @NotNull
    private String dateOfBirth;

}
