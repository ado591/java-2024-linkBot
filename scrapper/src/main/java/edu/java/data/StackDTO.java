package edu.java.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record StackDTO(
        @NotNull
        StackUser user,
        @NotNull
        @JsonProperty("question_id")
        Long id,
        @NotNull
        @JsonProperty("last_activity_date")
        OffsetDateTime lastActivity,
        @NotNull
        @JsonProperty("is_answered")
        Boolean isAnswered
){}
