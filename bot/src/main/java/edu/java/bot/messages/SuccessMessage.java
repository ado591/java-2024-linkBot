package edu.java.bot.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    SIGNUP_SUCCESS("Вы успешно зарегистрированы в системе. Воспользуйтесь командой /help для вызова справки"),
    LINK_ADDED("Ссылка успешно добавлена"),
    LINK_REMOVED("Ссылка удалена");
    private final String message;
}
