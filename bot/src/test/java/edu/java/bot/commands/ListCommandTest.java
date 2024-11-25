package edu.java.bot.commands;

import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListCommandTest extends CommandTest {
    @InjectMocks
    private ListCommand listCommand;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(listCommand.name(), CommandDescription.LIST.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(listCommand.description(), CommandDescription.LIST.getDescription());
    }

    @Test
    void emptyListTest() {
        Long userId = user.id();
        when(userManager.getGithubLinks()).thenReturn(Map.of(userId, new HashSet<>()));
        when(userManager.getStackOverflowLinks()).thenReturn(Map.of(userId, new HashSet<>()));
        SendMessage result = listCommand.execute(update);
        assertEquals("Список отслеживаемых ссылок пуст. Воспользуйтесь командой /track для добавления ссылки",
            result.getParameters().get("text"));
    }

    @Test
    void gitHubListTest() {
        Long userId = user.id();
        when(userManager.getGithubLinks()).thenReturn(Map.of(userId, new HashSet<>(
            Arrays.asList("https://github.com/rust-lang/rust",
                "https://github.com/nodejs/node"))));
        when(userManager.getStackOverflowLinks()).thenReturn(Map.of(userId, new HashSet<>()));
        String expectedResponse = """
            Список отслеживаемых ссылок:
            GitHub:
            https://github.com/nodejs/node
            https://github.com/rust-lang/rust
            """;
        SendMessage result = listCommand.execute(update);
        assertEquals(expectedResponse,
            result.getParameters().get("text"));
    }

    @Test
    void stackOverflowLinkTest() {
        Long userId = user.id();
        when(userManager.getStackOverflowLinks()).thenReturn(Map.of(userId, new HashSet<>(
            Arrays.asList("https://stackoverflow.com/questions/78023671/where-is-the-order-in-which-elf-relocations-are-applied-specified",
                "https://stackoverflow.com/questions/78049674/regex-to-split-a-column-in-r-after-the-second-pipe-and-after-the-second-t"))));
        when(userManager.getGithubLinks()).thenReturn(Map.of(userId, new HashSet<>()));
        String expectedResponse = """
            Список отслеживаемых ссылок:
            StackOverflow:
            https://stackoverflow.com/questions/78049674/regex-to-split-a-column-in-r-after-the-second-pipe-and-after-the-second-t
            https://stackoverflow.com/questions/78023671/where-is-the-order-in-which-elf-relocations-are-applied-specified
            """;
        SendMessage result = listCommand.execute(update);
        assertEquals(expectedResponse,
            result.getParameters().get("text"));
    }
}
