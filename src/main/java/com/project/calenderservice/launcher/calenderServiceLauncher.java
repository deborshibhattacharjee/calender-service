package com.project.calenderservice.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.project.calenderservice")
@EnableJpaRepositories(basePackages = "com.project.calenderservice.repository")
@EntityScan(basePackages = "com.project.calenderservice.entities")
public class calenderServiceLauncher {
    public static void main(String[] args) {
        SpringApplication.run(calenderServiceLauncher.class, args);
    }
}
