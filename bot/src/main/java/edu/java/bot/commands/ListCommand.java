package edu.java.bot.commands;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.messages.ErrorMessage;
import edu.java.bot.messages.InfoMessage;
import edu.java.bot.services.UserManager;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ListCommand implements Command {
    private final UserManager userManager;
    private final CommandDescription info = CommandDescription.LIST;

    @Override
    public String name() {
        return info.getName();
    }

    @Override
    public String description() {
        return info.getDescription(); }

    @Override
    public SendMessage execute(Update update) {
        StringBuilder response = new StringBuilder();

        Long userId = update.message().from().id();
        Set<String> githubLinks = userManager.getGithubLinks().get(userId);
        Set<String> stackOverflowLinks = userManager.getStackOverflowLinks().get(userId);

        if (githubLinks.isEmpty() && stackOverflowLinks.isEmpty()) {
            response.append(ErrorMessage.EMPTY_LIST.getMessage());
        } else {
            response.append(InfoMessage.LINK_LIST.getMessage());
            if (!githubLinks.isEmpty()) {
                response.append(InfoMessage.GITHUB_LINK.getMessage());
            }
            for (String link : githubLinks) {
                response.append(link).append("\n");
            }
            if (!stackOverflowLinks.isEmpty()) {
                response.append(InfoMessage.STACKOVERFLOW_LINK.getMessage());
            }
            for (String link : stackOverflowLinks) {
                response.append(link).append("\n");
            }
        }
        return new SendMessage(update.message().chat().id(), response.toString());
    }
}
