package edu.java.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record StackUser(
    @NotNull
    @JsonProperty("display_name")
    String topicCreator,
    @NotNull
    @JsonProperty("account_id")
    Long accountId
)
{}
