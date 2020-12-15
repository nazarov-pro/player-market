package com.shahinnazarov.gateway.container.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * SwaggerUrlsConfig - wrapper for {@link SwaggerUrl}s
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerUrlsConfig {
    private List<SwaggerUrl> urls;

}
