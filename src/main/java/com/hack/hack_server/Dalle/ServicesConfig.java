package com.hack.hack_server.Dalle;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Value("${app.openai.api}")
    private String apiKey;


    @Bean
    public OpenAiService getOpenAiService() {
        return new OpenAiService(apiKey);
    }
}