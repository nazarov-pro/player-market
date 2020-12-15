package com.shahinnazarov.gateway.container.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiEndpointDetails api endpoint details
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDocEndpointDetail {
    private String gatewayPrefix;
    private String serviceUrl;
    private String servicePath;

}
