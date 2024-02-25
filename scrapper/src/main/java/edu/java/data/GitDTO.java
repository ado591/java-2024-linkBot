package edu.java.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record GitDTO(
    @NotNull
    @JsonProperty("name")
    String repositoryName,
    @NotNull
    GitHubUser owner,
    @NotNull
    @JsonProperty("updated_at")
    OffsetDateTime updatedAt
){}
