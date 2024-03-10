package edu.java.bot.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    ALREADY_EXIST("Пользователь уже зарегистрирован"),
    EMPTY_LIST("Список отслеживаемых ссылок пуст. Воспользуйтесь командой /track для добавления ссылки"),
    INVALID_URL("Неверный формат ссылки. Попробуйте еще раз"),
    UNKNOWN_ERROR("Неизвестная ошибка"),
    UNSUPPORTED_LINK("Бот не поддерживает отслеживание данного ресурса"),
    UNKNOWN_LINK("Вы не отслеживаете данную ссылку"),
    UNKNOWN_COMMAND("Неизвестная команда. Вопользуйтесь командой /help для просмотра доступных команд"),
    NO_SUCH_USER("Для использования бота требуется регистрация. Нажмите /start, чтобы зарегистрироваться");
    private final String message;
}
