package com.shahinnazarov.team.container.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TeamRequest {
    private Long id;
    @Size(min = 1, max = 127)
    private String name;

}
