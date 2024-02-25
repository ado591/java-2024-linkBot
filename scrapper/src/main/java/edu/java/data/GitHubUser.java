package edu.java.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record GitHubUser(
    @NotNull
    @JsonProperty("login")
    String username
) {
}
