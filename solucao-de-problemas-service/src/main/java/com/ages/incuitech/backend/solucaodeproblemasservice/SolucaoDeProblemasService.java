package com.ages.incuitech.backend.solucaodeproblemasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SolucaoDeProblemasService {
    public static void main(String[] args) {
        SpringApplication.run(SolucaoDeProblemasService.class, args);
    }
}
