import exceptions.InvalidInputFormat;
import exceptions.InvalidNumberFormat;

import java.util.stream.Stream;

import static constants.Strings.*;

/**
 * Класс, выполняющий операции сложения, вычитания, умножения и целочисленного деления.
 */
public class Calculator {
    // объявляем статические строковые переменные (операции *, /, +, -) и разделитель (пробел)
    // Разделитель необходим для парсинга строки формата "операнд пробел операция пробел операнд"
    private static final String SPLITTER = " ";
    private static final String DIVISION = "/";
    private static final String MULTIPLICATION = "*";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";

    private Number leftOperand;
    private Number rightOperand;
    private String operation;

    /**
     * Конструктор калькулятора. Принимает сразу на вход строку с двумя операндами и операцией
     * (пример: "1 + 2", "I + IV")
     * @param value строковое выражение для подсчёта
     * @throws InvalidInputFormat выбрасывается, если формат ввода был неверным или была указана неверная операция
     * @throws InvalidNumberFormat выбрасывается, если неверным было само введенное число
     */
    Calculator(String value) throws InvalidInputFormat, InvalidNumberFormat {
        String[] values = value.split(SPLITTER);
        // если мы разделили строку, и оказалось меньше трёх значений (левый операнд, операция, правый операнд),
        // значит формат ввода неверный
        if (values.length < 3) {
            throw new InvalidInputFormat(NOT_ENOUGH_ARGUMENTS);
        }

        // если при разделении строки оказалось больше трёх значений, значит формат ввода был неверным
        if (values.length > 3) {
            throw new InvalidInputFormat(TOO_MUCH_ARGUMENTS);
        }

        this.leftOperand = new Number(values[0]);
        this.rightOperand = new Number(values[2]);

        // проверяем, совпадают ли типы операндов. Операнды должны
        // быть либо оба арабскими цифрами, либо оба римскими
        if (leftOperand.isRoman() && !rightOperand.isRoman() ||
                rightOperand.isRoman() && !leftOperand.isRoman()) {
            throw new InvalidInputFormat(OPERANDS_TYPE_MISMATCH);
        }

        this.operation = values[1];
        // проверяем, соответствует ли указанная в строке операция доступным
        // (доступны *, /, +, -)
        if (Stream.of(DIVISION, MULTIPLICATION, ADDITION, SUBTRACTION).noneMatch(this.operation::equalsIgnoreCase)) {
           throw new InvalidInputFormat(NO_SUCH_OPERATION);
        }
    }

    public String calculate() {
        Number result = null;
        int arabicResult = -1;
        // по первому операнду смотрим, какого типа будет операция
        // необходимо для того, чтобы впоследствии вывести результат либо римскими цифрами, либо арабскими
        boolean isOperationRoman = this.leftOperand.isRoman();

        // выбираем нужную операцию и выполняем её
        switch (this.operation) {
            case DIVISION: {
                arabicResult = this.leftOperand.value() / this.rightOperand.value();
            } break;
            case MULTIPLICATION: {
                arabicResult = this.leftOperand.value() * this.rightOperand.value();
            } break;
            case ADDITION: {
                arabicResult = this.leftOperand.value() + this.rightOperand.value();
            } break;
            case SUBTRACTION: {
                arabicResult = this.leftOperand.value() - this.rightOperand.value();
            }
        }

        try {
            // создаём результат, указывая, какого типа была операция.
            // Класс Number выполнит преобразование в римские цифры, если это нужно
            result = new Number(arabicResult, isOperationRoman);
        } catch (InvalidNumberFormat invalidNumberFormat) {
            // на всякий случай ловим исключение, но в принципе оно выбрасываться не должно, поскольку
            // на этапе подсчёта уже известно, что все аргументы были верными, и результат тоже
            // должен быть корректным.
            System.out.println(invalidNumberFormat.getMessage());

            return EMPTY_STRING;
        }

        // если результат - римская цифра, то выводим значение в формате римской цифры, иначе выводим арабскую
        return result.isRoman() ? result.romanValue() : String.valueOf(result.value());
    }
}
