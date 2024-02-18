package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StartTest extends CommandTest {
    @InjectMocks
    private Start startCommand;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(startCommand.name(), CommandDescription.START.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(startCommand.description(), CommandDescription.START.getDescription());
    }

}
