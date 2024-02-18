package edu.java.bot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandDescription {
    START("/start", "зарегистрировать пользователя"),
    HELP("/help", "вывести окно с командами"),
    TRACK("/track", "начать отслеживание ссылки"),
    UNTRACK("/untrack", "прекратить отслеживание ссылки"),
    LIST("/list", "показать список отслеживаемых ссылок");
    private final String name;
    private final String description;
}
