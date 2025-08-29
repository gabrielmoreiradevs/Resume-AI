package dev.gabrielmoreira.ResumoAI.Controller;

import dev.gabrielmoreira.ResumoAI.Service.ChatGPTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private final ChatGPTService chatGptService;

    public RecipeController(ChatGPTService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/recipe")
    public Mono<ResponseEntity<String>> generatRecipe() {
        return chatGptService.generatRecipe()
                .map(resultado -> ResponseEntity.ok(resultado));
    }

}
