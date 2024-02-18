package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HelpTest extends CommandTest {
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

}
