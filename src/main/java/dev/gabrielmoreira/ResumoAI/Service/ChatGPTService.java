package dev.gabrielmoreira.ResumoAI.Service;

import com.fasterxml.jackson.databind.JsonNode;
import dev.gabrielmoreira.ResumoAI.DTO.BookItemDTO;
import dev.gabrielmoreira.ResumoAI.Model.Bookitem;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGPTService {

    private final WebClient client;
    private final String apikey=System.getenv("API-KEY");

    public ChatGPTService(WebClient client) {
        this.client = client;
    }

    public Mono<String> generateSummariesAndSuggestions(List<BookItemDTO> bookitems) {

        String livros = bookitems.stream()
                .map(item-> String.format("• %s (por %s) [%s]", item.getTitulo(), item.getAutor(), item.getGenero()))
                .collect(Collectors.joining("\n"));
        String prompt =  "A seguir está uma lista dos meus livros. Para cada um, forneça um resumo curto de uma ou duas frases. " +
                "Depois, com base nos gêneros e autores da lista, sugira 3 novos livros que eu poderia gostar, explicando o porquê da sugestão.\n\n" +
                "Meus Livros:\n" + livros;

        return client.post()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apikey)
                .bodyValue(Map.of(
                        "model", "gpt-4o-mini",
                        "input", prompt
                ))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    JsonNode textNode = json.path("output").get(0).path("content").get(0).path("text");
                    return textNode.isMissingNode() ? "O modelo não retornou nenhuma resposta." : textNode.asText();
                });

    }

}
