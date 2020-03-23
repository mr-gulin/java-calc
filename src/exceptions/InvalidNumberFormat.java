package exceptions;

import static constants.Strings.COLON_DELIMITER;
import static constants.Strings.INVALID_NUMBER_FORMAT;

/**
 * Исключение для неверного формата цифры (как арабской, так и римской)
 */
public class InvalidNumberFormat extends Exception {
    /**
     * Стандартное сообщение исключения
     */
    private static final String exceptionMessage = INVALID_NUMBER_FORMAT;

    /**
     * Конструктор для вывода стандартного сообщения при исключении
     */
    public InvalidNumberFormat() {
        super(exceptionMessage);
    }

    /**
     * Конструктор для вывода сообщения с дополнением при исключении
     * @param customMessage - дополнительное сообщение для исключения
     */
    public InvalidNumberFormat(String customMessage) {
        super(exceptionMessage.concat(COLON_DELIMITER).concat(customMessage));
    }
}
