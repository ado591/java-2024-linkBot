package edu.java.bot.commands;

import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void addNewUserTest() {
        when(userManager.containsUser(user)).thenReturn(false);
        SendMessage result = startCommand.execute(update);
        assertEquals("Вы успешно зарегистрированы в системе. Воспользуйтесь командой /help для вызова справки",
            result.getParameters().get("text"));
        verify(userManager).add(user);
    }

    @Test
    void addExistingUserTest() {
        when(userManager.containsUser(user)).thenReturn(true);
        SendMessage result = startCommand.execute(update);
        assertEquals("Пользователь уже зарегистрирован", result.getParameters().get("text"));
        verify(userManager, never()).add(user);
    }

}
