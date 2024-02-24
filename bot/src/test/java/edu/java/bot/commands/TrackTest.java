package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrackTest extends CommandTest {
    @InjectMocks
    private Track trackCommand;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(trackCommand.name(), CommandDescription.TRACK.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(trackCommand.description(), CommandDescription.TRACK.getDescription());
    }

}
