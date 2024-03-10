package edu.java.configuration;

import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ClientConfiguration {
    final ApplicationConfig config;

    @Value("${clients.github}")
    private String gitBaseUrl;
    @Value("${clients.stackoverflow}")
    private String stackBaseUrl;

    public ClientConfiguration(ApplicationConfig config) {
        this.config = config;
    }

    @Bean
    public GitHubClient gitHubClient() {
        return new GitHubClient(gitBaseUrl);
    }

    @Bean
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient(stackBaseUrl);
    }
}
