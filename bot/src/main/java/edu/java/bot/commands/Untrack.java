package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.services.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class Untrack implements Command {
    private final UserManager userManager;
    private final CommandDescription info = CommandDescription.UNTRACK;

    @Override
    public String name() {
        return info.getName();
    }

    @Override
    public String description() {
        return info.getDescription(); }

    @Override
    public SendMessage execute(Update update) throws URISyntaxException {
        Long chatId = update.message().chat().id();
        String[] messages = update.message().text().split(" +", 2);
        String response = userManager.removeLink(update.message().from(), messages[messages.length - 1]);
        return new SendMessage(chatId, response);
    }
}
