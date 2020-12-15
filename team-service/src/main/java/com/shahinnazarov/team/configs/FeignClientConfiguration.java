package com.shahinnazarov.team.configs;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Feign Client Configuration
 */
@Configuration
@EnableFeignClients(basePackages = "com.shahinnazarov.team.adapters")
@EnableCircuitBreaker
public class FeignClientConfiguration {
    @Bean
    public Decoder feignDecoder() {
        return new SpringDecoder(messageConverters());
    }

    @Bean
    public Encoder feignEncoder() {
        return new SpringEncoder(messageConverters());
    }

    private ObjectFactory<HttpMessageConverters> messageConverters() {
        return () -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }

}
