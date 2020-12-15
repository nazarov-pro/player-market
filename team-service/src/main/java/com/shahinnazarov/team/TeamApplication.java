package com.shahinnazarov.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main execution point of the Team Service Application
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TeamApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TeamApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
