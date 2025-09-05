package dev.gabrielmoreira.ResumoAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.api.url}")
    private String chatGptUrl="https://api.openai.com/v1/responses";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(chatGptUrl).build();
    }

}
