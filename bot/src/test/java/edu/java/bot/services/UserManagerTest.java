package edu.java.bot.services;

import com.pengrad.telegrambot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserManagerTest {
    private UserManager userManager;
    private LinkManager linkManager;
    private final Long testId = 123123123L;
    private User user;

    @BeforeEach
    void setUp() {
        linkManager = mock(LinkManager.class);
        userManager = new UserManager(linkManager);
        user = new User(testId);
    }

    @Test
    void testAddUser() {
        userManager.add(user);
        Map<Long, User> allUsers = userManager.getAllUsers();
        assertTrue(allUsers.containsKey(testId));
        assertEquals(user, allUsers.get(testId));
    }

    @Test
    void testContainsUser() {
        userManager.add(user);
        assertTrue(userManager.containsUser(user));
    }

    @Test
    void testAddValidLink() throws URISyntaxException {
        userManager.add(user);
        String validLink = "https://github.com/tamarinvs19/theory_university";
        when(linkManager.isValid(validLink)).thenReturn(true);
        URI uri = linkManager.makeURI(validLink);
        when(linkManager.isGitLink(uri)).thenReturn(true);
        assertEquals("Ссылка успешно добавлена", userManager.addLink(user, validLink));
        assertTrue(userManager.getGithubLinks().get(testId).contains(validLink));
    }

    @Test
    void testAddInvalidLink() throws URISyntaxException {
        String invalidLink = "invalid";
        when(linkManager.isValid(invalidLink)).thenReturn(false);
        assertEquals("Неверный формат ссылки. Попробуйте еще раз", userManager.addLink(user, invalidLink));
    }

    @Test
    void testRemoveExistingLink() throws URISyntaxException {
        String link = "https://github.com/tamarinvs19/theory_university";
        userManager.add(user);
        userManager.getGithubLinks().get(testId).add(link);
        when(linkManager.isGitLink(any())).thenReturn(true);
        when(linkManager.isValid(any())).thenReturn(true);
        assertEquals("Ссылка удалена", userManager.removeLink(user, link));
        assertFalse(userManager.getGithubLinks().get(testId).contains(link));
    }

    @Test
    void testRemoveNonExistingLink() throws URISyntaxException {
        String link = "https://github.com/tamarinvs19/theory_university";
        userManager.add(user);
        when(linkManager.isGitLink(any())).thenReturn(true);
        when(linkManager.isValid(any())).thenReturn(true);
        assertEquals("Вы не отслеживаете данную ссылку", userManager.removeLink(user, link));
    }
}
