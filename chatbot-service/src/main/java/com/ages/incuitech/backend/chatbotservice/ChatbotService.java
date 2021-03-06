package com.ages.incuitech.backend.chatbotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ChatbotService {
    public static void main(String[] args) {
        SpringApplication.run(ChatbotService.class, args);
    }
}
