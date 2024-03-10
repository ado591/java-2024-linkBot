package edu.java.bot.services;

import java.net.URI;
import java.net.URISyntaxException;

public interface LinkValidator {

    boolean isValid(String s);

    URI makeURI(String s) throws URISyntaxException;

    boolean isGitLink(URI link);

    boolean isStackLink(URI link);

}
