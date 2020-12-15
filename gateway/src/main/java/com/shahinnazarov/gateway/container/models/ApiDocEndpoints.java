package com.shahinnazarov.gateway.container.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * ApiEndpoints stores {@link ApiDocEndpointDetail}s
 */
@Configuration
@ConfigurationProperties(prefix = "api-docs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiDocEndpoints {
    private Map<String, ApiDocEndpointDetail> endpoints;

}
