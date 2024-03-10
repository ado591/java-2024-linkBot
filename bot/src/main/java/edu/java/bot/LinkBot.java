package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.commands.Command;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.services.UserMessageProcessor;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class LinkBot extends TelegramBot {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserMessageProcessor userMessageProcessor;

    @Autowired
    public LinkBot(ApplicationConfig config, UserMessageProcessor userMessageProcessor, List<Command> commands) {
        super(config.telegramToken());
        this.userMessageProcessor = userMessageProcessor;

        List<BotCommand> botCommands = commands.stream().map(Command::toApiCommand).toList();
        execute(new SetMyCommands(botCommands.toArray(BotCommand[]::new)));
        setUpdatesListener(this::processUpdate, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                LOGGER.error("Some problem with updates from telegram");
            }
        });
    }

    public int processUpdate(List<Update> updates) {
        for (Update update : updates) {
            if (!Objects.isNull(update.message())) {
                try {
                    execute(userMessageProcessor.process(update));
                } catch (URISyntaxException e) {
                    LOGGER.error("Failed to process update", e);
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
