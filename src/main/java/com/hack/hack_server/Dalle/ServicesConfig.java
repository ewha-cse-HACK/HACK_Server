package com.hack.hack_server.Dalle;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ServicesConfig {
    @Value("${app.openai.api}")
    private String apiKey;


    @Bean
    public OpenAiService getOpenAiService() {
        //socket timeout ERROR 해결:
        return new OpenAiService(apiKey, Duration.ofSeconds(30));
//        return new OpenAiService(apiKey);
    }
}