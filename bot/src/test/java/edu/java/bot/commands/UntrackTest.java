package edu.java.bot.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UntrackTest extends CommandTest {
    @InjectMocks
    private Track track;

    @Test
    @Override
    void commandNameTest() {
        assertEquals(track.name(), CommandDescription.TRACK.getName());
    }

    @Test
    @Override
    void commandDescriptionTest() {
        assertEquals(track.description(), CommandDescription.TRACK.getDescription());
    }


}
