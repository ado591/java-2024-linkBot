package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListCommandTest extends CommandTest {
    @InjectMocks
    private ListCommand listCommand;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(listCommand.name(), CommandDescription.START.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(listCommand.description(), CommandDescription.START.getDescription());
    }


}
