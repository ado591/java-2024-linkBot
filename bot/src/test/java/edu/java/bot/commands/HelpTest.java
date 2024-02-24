package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class HelpTest extends CommandTest {
    @Autowired
    List<Command> allCommands;

    @InjectMocks
    private Help helpCommand;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(helpCommand.name(), CommandDescription.HELP.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(helpCommand.description(), CommandDescription.HELP.getDescription());
    }

    @Test
    void containsSameCommandsTest() {
        List<String> expectedNames = List.of("/help", "/list", "/start", "/track", "/untrack");
        List<String> commandNames = allCommands.stream().map(Command::name).toList();
        assertEquals(commandNames, expectedNames);

    }

    @Test
    void containsSameDescriptionTest() {
        List<String> expectedDescription = List.of("вывести окно с командами", "показать список отслеживаемых ссылок",
            "зарегистрировать пользователя", "начать отслеживание ссылки", "прекратить отслеживание ссылки");
        List<String> commandDescription = allCommands.stream().map(Command::description).toList();
        assertEquals(commandDescription, expectedDescription);
    }
}
