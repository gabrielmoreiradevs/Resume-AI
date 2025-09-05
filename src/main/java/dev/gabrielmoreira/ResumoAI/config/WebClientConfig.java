package dev.gabrielmoreira.ResumoAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private String chatGptUrl=System.getenv("OPENAI_API_URL");;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(chatGptUrl).build();
    }

}
