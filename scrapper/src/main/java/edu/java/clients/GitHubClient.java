package edu.java.clients;

import edu.java.data.GitDTO;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubClient {
    private final WebClient webClient;
    private String baseUrl = "https://api.github.com";


    public GitHubClient(String otherUrl) {
        if (!otherUrl.isEmpty()) {
            this.baseUrl = otherUrl;
        }
        this.webClient = WebClient.builder().baseUrl(otherUrl).build();
    }

    public GitDTO fetch(String username, String repositoryName) {
        return webClient.get().uri("/repos/%s/%s".formatted(username, repositoryName))
            .retrieve()
            .bodyToMono(GitDTO.class)
            .block();
    }
}
