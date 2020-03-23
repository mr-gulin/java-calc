package exceptions;

import static constants.Strings.COLON_DELIMITER;
import static constants.Strings.INVALID_INPUT_FORMAT;

/**
 *  Исключение для неправильного формата ввода
 */
public class InvalidInputFormat extends Exception{
    /**
     *  Стандартное сообщение исключения
     */
    private static final String exceptionMessage = INVALID_INPUT_FORMAT;

    /**
     *  Конструктор для вывода стандартного сообщения при исключении
     */
    public InvalidInputFormat() {
        super(exceptionMessage);
    }

    /**
     * Конструктор для вывода сообщения с дополнительным пояснением при исключении
     * @param customMessage - дополнительное сообщение для исключения
     */
    public InvalidInputFormat(String customMessage) {
        super(exceptionMessage.concat(COLON_DELIMITER).concat(customMessage));
    }
}
