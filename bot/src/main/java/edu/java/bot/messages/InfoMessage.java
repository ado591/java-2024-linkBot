package edu.java.bot.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoMessage {
    SUPPORTED_COMMANDS("Список команд:\n"),
    SUPPORTED_SOURCE("Сайты доступные для отслеживания:\n"),
    LINK_LIST("Список отслеживаемых ссылок:\n"),
    GITHUB_LINK("GitHub:\n"),
    STACKOVERFLOW_LINK("StackOverflow:\n");
    private final String message;
}
