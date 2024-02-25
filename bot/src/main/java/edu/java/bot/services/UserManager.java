package edu.java.bot.services;

import com.pengrad.telegrambot.model.User;
import edu.java.bot.messages.ErrorMessage;
import edu.java.bot.messages.SuccessMessage;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@RequiredArgsConstructor
@Repository
public class UserManager {
    private final LinkManager linkManager;
    private Map<Long, User> allUsers = new HashMap<>();
    private Map<Long, HashSet<String>> stackOverflowLinks = new HashMap<>();
    private Map<Long, HashSet<String>> githubLinks = new HashMap<>();

    public void add(User user) {
        allUsers.put(user.id(), user);
        stackOverflowLinks.put(user.id(), new HashSet<>());
        githubLinks.put(user.id(), new HashSet<>());
    }

    public boolean containsUser(User user) {
        return allUsers.containsKey(user.id());
    }


    public String addLink(User user, String link) throws URISyntaxException {
        if (!linkManager.isValid(link)) {
            return ErrorMessage.INVALID_URL.getMessage();
        }
        URI uri = linkManager.makeURI(link);
        if (linkManager.isGitLink(uri)) {
            githubLinks.get(user.id()).add(link);
        } else if (linkManager.isStackLink(uri)) {
            stackOverflowLinks.get(user.id()).add(link);
        } else {
            return ErrorMessage.UNSUPPORTED_LINK.getMessage();
        }
        return SuccessMessage.LINK_ADDED.getMessage();
    }

    public String removeLink(User user, String link) throws URISyntaxException {
        if (!linkManager.isValid(link)) {
            return ErrorMessage.INVALID_URL.getMessage();
        }
        URI uri = linkManager.makeURI(link);
        if (linkManager.isGitLink(uri) && githubLinks.get(user.id()).contains(link)) {
            githubLinks.get(user.id()).remove(link);
        } else if (linkManager.isStackLink(uri) && stackOverflowLinks.get(user.id()).contains(link)) {
            stackOverflowLinks.get(user.id()).remove(link);
        } else {
            return ErrorMessage.UNKNOWN_LINK.getMessage();
        }
        return SuccessMessage.LINK_REMOVED.getMessage();
    }
}
