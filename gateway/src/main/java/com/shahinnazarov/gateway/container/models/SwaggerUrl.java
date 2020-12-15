package com.shahinnazarov.gateway.container.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SwaggerUrl (swagger expected url model)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerUrl {
    private String url;
    private String name;

}
