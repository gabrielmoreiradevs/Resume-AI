package dev.gabrielmoreira.ResumoAI.Service;

import dev.gabrielmoreira.ResumoAI.config.WebClientConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
public class ChatGPTService {

    private final WebClient client;
    private String apikey=System.getenv("API-KEY");

    public ChatGPTService(WebClient client, WebClientConfig config) {
        this.client = client;
    }

    public Mono<String> generatRecipe() {
        String prompt = "Agora você é um bibliotecário virtual especializado em literatura e estudos acadêmicos. Sua função é me fornecer resumos detalhados, leituras complementares e informações contextuais sobre livros que eu solicitar. Sempre que eu informar o título de um livro, você deverá fornecer um resumo conciso e completo da obra, destacando os pontos principais, sugerir leituras complementares ou referências relacionadas ao tema do livro, indicar conceitos importantes ou ideias centrais que devem ser compreendidas para aprofundar o estudo, e sempre manter um tom educativo, claro e acessível, como se estivesse me guiando pessoalmente na biblioteca. Se algum livro não tiver informações suficientes, explique isso e indique fontes alternativas ou obras semelhantes que possam ajudar no estudo.";

        return client.post()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apikey)
                .bodyValue(Map.of(
                        "model", "gpt-4o-mini",
                        "input", prompt
                ))
                .retrieve()
                .bodyToMono(String.class);
    }

}
