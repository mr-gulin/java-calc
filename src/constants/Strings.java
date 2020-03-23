package constants;

public class Strings {
    // объявляем статические константы строк для исключений
    public static final String INVALID_INPUT_FORMAT = "Неверный формат ввода";
    public static final String INVALID_NUMBER_FORMAT = "Неверный формат числа";
    public static final String NOT_ENOUGH_ARGUMENTS = "Недостаточно аргументов";
    public static final String TOO_MUCH_ARGUMENTS = "Слишком много аргументов";
    public static final String OPERANDS_TYPE_MISMATCH = "Аргументы должны быть оба римскими либо оба арабскими цифрами";
    public static final String NO_SUCH_OPERATION = "Недопустимая операция. Доступные операции: *, /, +, -";
    public static final String VALUE_OUT_OF_RANGE = "Число должно быть от 1 до 10 (включительно)";
    public static final String CANT_CONVERT_TO_ARABIC = "Ошибка конвертирования %s в арабское число!";
    public static final String CANT_CONVERT_TO_ROMAN = "Ошибка конвертирования %s в римское число!";

    // объявляем некоторые строковые константы, использующиеся при выводе строк (разделители)
    public static final String EMPTY_STRING = "";
    public static final String COLON_DELIMITER = ": ";
    public static final String DASH_DELIMITER = " - ";

    public static final String ENTER_EXPRESSION = "Введите выражение:";
}
