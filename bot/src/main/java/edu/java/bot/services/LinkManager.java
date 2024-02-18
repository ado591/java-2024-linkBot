package edu.java.bot.services;

import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Component
@SuppressWarnings("checkstyle:MemberName")
public class LinkManager implements LinkValidator {
    private Logger LOGGER = LogManager.getLogger();
    private final String github = "github.com";
    private final String stackOverflow = "stackoverflow.com";
    private final String linkPattern =
        "^(https?)(://)\\w+.\\w{2,}/*[a-zA-Z0-9~@#%&+-=_/|]*";

    @Override
    public boolean isValid(String s) {
        return Pattern.matches(linkPattern, s);
    }

    @Override
    public URI makeURI(String link) throws URISyntaxException {
        try {
            return new URI(link);
        } catch (URISyntaxException e) {
            LOGGER.error("ERROR: given link is invalid");
            return null;
        }
    }

    @Override
    public boolean isGitLink(URI link) {
        return github.equals(link.getHost());
    }

    @Override
    public boolean isStackLink(URI link) {
        return stackOverflow.equals(link.getHost());
    }

}
