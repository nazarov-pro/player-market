package com.shahinnazarov.team.container.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Player Query Request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerQueryRequest {
    private Collection<Long> ids;

}
