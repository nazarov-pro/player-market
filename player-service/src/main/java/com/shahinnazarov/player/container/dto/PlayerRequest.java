package com.shahinnazarov.player.container.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class PlayerRequest {
    private Long id;
    @Size(min = 1, max = 63)
    private String firstname;
    @Size(min = 1, max = 63)
    private String lastname;
    @PositiveOrZero
    private Short monthOfExperience;
    @NotNull
    private String dateOfBirth;

}
