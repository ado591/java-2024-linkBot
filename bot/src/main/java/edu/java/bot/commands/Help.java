package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.messages.InfoMessage;
import edu.java.bot.services.UserManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Help implements Command {
    private final List<Command> allComands;
    private final CommandDescription info = CommandDescription.HELP;

    @Override
    public String name() {
        return info.getName();
    }

    @Override
    public String description() {
        return info.getDescription();
    }

    @Override
    public SendMessage execute(Update update) {
        StringBuilder response = new StringBuilder();
        response.append(InfoMessage.SUPPORTED_COMMANDS.getMessage());
        for (Command botCommand: allComands) {
            response.append(botCommand.name());
            response.append(" : ").append(botCommand.description()).append("\n");
        }
        return new SendMessage(update.message().chat().id(), response.toString());
    }

}
