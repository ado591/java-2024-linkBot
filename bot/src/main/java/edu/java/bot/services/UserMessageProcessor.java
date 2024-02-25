package edu.java.bot.services;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.CommandDescription;
import edu.java.bot.messages.ErrorMessage;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageProcessor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserManager userManager;
    private final Map<String, Command> botCommands;

    @Autowired
    public UserMessageProcessor(List<Command> botCommands, UserManager userManager) {
        this.userManager = userManager;
        this.botCommands = new HashMap<>();

        for (Command command: botCommands) {
            this.botCommands.put(command.name(), command);
        }
    }

    public SendMessage process(Update update) throws URISyntaxException {
        if (Objects.isNull(update.message())) {
            return null;
        }
        Long chatId = update.message().chat().id();
        User producer = update.message().from();
        String userMessage = update.message().text();
        String commandName = userMessage.split(" +")[0];

        LOGGER.info("User with chatId = %d has sent %s".formatted(chatId, userMessage));
        if (userManager.containsUser(producer) || commandName.equals(CommandDescription.START.getName())) {
            Command command = botCommands.get(commandName);
            if (!Objects.isNull(command)) {
                LOGGER.info("Executing command: %s".formatted(command.name()));
                return command.execute(update);
            } else {
                LOGGER.info("No such command: %s".formatted(commandName));
                new SendMessage(chatId, ErrorMessage.UNKNOWN_COMMAND.getMessage());
            }
        } else {
            LOGGER.info("Cannot find user with chatId = %d".formatted(chatId));
            new SendMessage(chatId, ErrorMessage.NO_SUCH_USER.getMessage());
        }
        return new SendMessage(chatId, ErrorMessage.UNKNOWN_ERROR.getMessage());
    }
}
