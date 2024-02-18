package edu.java.bot.commands;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.net.URISyntaxException;

public interface Command {
    String name();

    String description();

    SendMessage execute(Update update) throws URISyntaxException;

    default BotCommand toApiCommand() {
        return new BotCommand(name(), description());
    }

}
