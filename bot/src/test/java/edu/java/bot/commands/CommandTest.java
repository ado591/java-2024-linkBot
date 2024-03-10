package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import edu.java.bot.services.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public abstract class CommandTest {
    @Mock
    protected UserManager userManager;

    @Mock
    protected List<Command> allCommands;

    @Mock
    protected Update update;

    @Mock
    protected Message message;

    @Mock
    protected User user;

    @Mock
    protected Chat chat;



    @BeforeEach
    public void setUp() {
        lenient().when(update.message()).thenReturn(message);
        lenient().when(update.message().from()).thenReturn(user);
        lenient().when(user.id()).thenReturn(666L);
        lenient().when(message.chat()).thenReturn(chat);
    }

    abstract void commandNameTest();
    abstract void commandDescriptionTest();
}
