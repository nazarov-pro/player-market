package com.shahinnazarov.team.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahinnazarov.common.container.exceptions.AbstractBaseException;
import com.shahinnazarov.common.container.models.ApiError;
import com.shahinnazarov.common.container.models.ApiMetadata;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Global exception handler
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@Order(-2)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private final ApiResponseGenerator responseGenerator;
    @Qualifier("json")
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Global security error, ", ex);
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        ApiMetadata apiMetadata = new ApiMetadata();
        apiMetadata.setStatus("OPERATION_FAILED");
        apiMetadata.setSuccess(false);

        ApiError apiError = new ApiError();

        if (ex instanceof AbstractBaseException) {
            AbstractBaseException baseException = (AbstractBaseException) ex;
            apiError.setCode(baseException.getCode());
            apiError.setDescription(baseException.getDescription());
            apiError.setReason(baseException.getReason());
            exchange.getResponse().setRawStatusCode(baseException.getHttpStatusCode());
        } else if (ex instanceof ResponseStatusException) {
            HttpStatus status = ((ResponseStatusException) ex).getStatus();
            apiError.setCode("STATUS_CODE");
            apiError.setReason(ex.getMessage());
            apiError.setDescription(ex.getMessage());
            exchange.getResponse().setStatusCode(status);
        } else {
            String message = ex.getMessage().length() > 32 ? ex.getMessage().substring(0, 32).concat("...") :
                    ex.getMessage();
            apiError.setCode("UNKNOWN");
            apiError.setReason(message);
            apiError.setDescription(message);
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        apiMetadata.setErrors(Collections.singletonList(apiError));

        DataBuffer dataBuffer;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(responseGenerator.generateMessage(apiMetadata)));
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        } catch (JsonProcessingException e) {
            log.error("Something terrible occurred, ", e);
            dataBuffer = bufferFactory.wrap("Serialization problem occurred.".getBytes(StandardCharsets.UTF_8));
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
