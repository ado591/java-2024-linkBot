package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@Value("${APP_TELEGRAM_TOKEN}")
    @NotEmpty
    String telegramToken
) {
}
