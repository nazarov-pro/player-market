package com.shahinnazarov.player.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Player Search Request DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerQueryRequest {
    @NotNull
    private Collection<Long> ids;

}
