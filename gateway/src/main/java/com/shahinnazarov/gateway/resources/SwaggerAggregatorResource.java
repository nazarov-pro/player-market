package com.shahinnazarov.gateway.resources;

import com.shahinnazarov.gateway.container.models.ApiDocEndpoints;
import com.shahinnazarov.gateway.container.models.SwaggerUrl;
import com.shahinnazarov.gateway.container.models.SwaggerUrlsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger Config publisher resource
 */
@RestController
@RequiredArgsConstructor
public class SwaggerAggregatorResource {
    private final ApiDocEndpoints endpoints;

    @GetMapping("/swagger-config.json")
    public SwaggerUrlsConfig swaggerConfig() {
        List<SwaggerUrl> urls = endpoints.getEndpoints()
                .entrySet().stream().map(routeEntry -> {
                    String name = routeEntry.getKey();
                    return SwaggerUrl.builder()
                            .url(routeEntry.getValue().getGatewayPrefix()
                                    + routeEntry.getValue().getServicePath())
                            .name(name).build();
                }).collect(Collectors.toList());
        return new SwaggerUrlsConfig(urls);
    }

}
