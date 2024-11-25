package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.messages.ErrorMessage;
import edu.java.bot.messages.SuccessMessage;
import edu.java.bot.services.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Start implements Command {
    private final UserManager userManager;
    private final CommandDescription info = CommandDescription.START;

    @Override
    public String name() {
        return info.getName();
    }

    @Override
    public String description() {
        return info.getDescription(); }

    @Override
    public SendMessage execute(Update update) {
        User producer = update.message().from();
        String response = "";
        if (!userManager.containsUser(producer)) {
            userManager.add(update.message().from());
            response = SuccessMessage.SIGNUP_SUCCESS.getMessage();
        } else {
            response = ErrorMessage.ALREADY_EXIST.getMessage();
        }
        return new SendMessage(update.message().chat().id(), response);
    }
}
