package dev.gabrielmoreira.ResumoAI.Controller;

import dev.gabrielmoreira.ResumoAI.DTO.BookItemDTO;
import dev.gabrielmoreira.ResumoAI.Mapper.BookItemMapper;
import dev.gabrielmoreira.ResumoAI.Service.BookItemService;
import dev.gabrielmoreira.ResumoAI.Service.ChatGPTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RecipeController {

    private final ChatGPTService chatGptService;
    private final BookItemService bookItemService;
    private final BookItemMapper bookItemMapper;

    public RecipeController(ChatGPTService chatGptService, BookItemService bookItemService, BookItemMapper bookItemMapper) {
        this.chatGptService = chatGptService;
        this.bookItemService = bookItemService;
        this.bookItemMapper = bookItemMapper;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe() {
            List<BookItemDTO> bookitems = bookItemService.verLivros();

        return chatGptService.generateSummariesAndSuggestions (bookitems)
                .map(resultado -> ResponseEntity.ok(resultado))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
