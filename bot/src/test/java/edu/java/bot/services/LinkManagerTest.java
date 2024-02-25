package edu.java.bot.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LinkManagerTest {
    private LinkManager linkManager;
    @BeforeEach
    void init() {
        linkManager = new LinkManager();
    }

    @ParameterizedTest
    @CsvSource({"https://github.com, true", "affdkmosi.., false"})
    void testIsValid(String link, boolean expected) {
        assertEquals(expected, linkManager.isValid(link));
    }

    @ParameterizedTest
    @CsvSource({"https://github.com, true", "https://stackoverflow.com, true"})
    void testMakeURI(String link, boolean expected) throws URISyntaxException {
        try {
            assertEquals(expected, linkManager.makeURI(link) != null);
        } catch (URISyntaxException e) {
            assertFalse(expected);
        }
    }

    @ParameterizedTest
    @CsvSource({"https://github.com/tamarinvs19/theory_university, true", "https://stackoverflow.com, false",
        "https://habr.com/ru/articles/591587/, false"})
    void testIsGitLink(String link, boolean expectedResult) {
        try {
            assertEquals(expectedResult, linkManager.isGitLink(new URI(link)));
        } catch (URISyntaxException e) {
            Assertions.fail("invalid URI caused exception");
        }
    }

    @ParameterizedTest
    @CsvSource({"https://stackoverflow.com, true", "https://github.com, false",
        "https://habr.com/ru/articles/591587/, false"})
    void testIsStackLink(String link, boolean expectedResult) {
        try {
            assertEquals(expectedResult, linkManager.isStackLink(new URI(link)));
        } catch (URISyntaxException e) {
            Assertions.fail("invalid URI caused exception");
        }
    }
}
