package com.shahinnazarov.team.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.common.utils.TimeUtils;
import com.shahinnazarov.common.utils.UUIDGenerator;
import com.shahinnazarov.common.utils.impl.ApiResponseGeneratorImpl;
import com.shahinnazarov.common.utils.impl.TimeUtilsImpl;
import com.shahinnazarov.common.utils.impl.UUIDGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean("json")
    @Primary
    public ObjectMapper jsonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //pretty print
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

    @Bean
    public TimeUtils timeUtils() {
        return new TimeUtilsImpl();
    }

    @Bean
    public UUIDGenerator uuidGenerator() {
        return new UUIDGeneratorImpl();
    }

    @Bean
    public ApiResponseGenerator apiResponseGenerator() {
        return new ApiResponseGeneratorImpl();
    }
}
