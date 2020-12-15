package com.shahinnazarov.sd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(
        classes = ServiceDiscoveryApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class ServiceDiscoveryApplicationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Application catalog test [Integration Test]")
    @Test
    void catalogTest() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/eureka/apps", Map.class
        );
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @DisplayName("Application health check test [Integration Test]")
    @Test
    void healthTest() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/actuator/health", Map.class
        );
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
