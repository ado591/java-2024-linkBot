package edu.java.clients;

import edu.java.data.StackDTO;
import org.springframework.web.reactive.function.client.WebClient;


public class StackOverflowClient {
    private final WebClient webClient;
    private String baseUrl = "https://api.stackexchange.com/2.3";

    public StackOverflowClient(String otherUrl) {
        if (!otherUrl.isEmpty()) {
            this.baseUrl = otherUrl;
        }
        this.webClient = WebClient.builder().baseUrl(otherUrl).build();
    }

    public StackDTO fetch(Long questionId) {
        return webClient.get().uri(param -> param
                .path("/questions/%d".formatted(questionId))
                .queryParam("site", "stackoverflow").build())
            .retrieve().bodyToMono(StackDTO.class).block();
    }
}
