package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UntrackTest extends CommandTest {
    @InjectMocks
    private Untrack untrack;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(untrack.name(), CommandDescription.UNTRACK.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(untrack.description(), CommandDescription.UNTRACK.getDescription());
    }

}
