package com.shahinnazarov.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main execution point of the Player Service Application
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PlayerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PlayerApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
