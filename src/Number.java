import exceptions.InvalidNumberFormat;

import java.io.Serializable;
import java.util.List;

import static constants.Strings.*;

/**
 *  Класс, который используется для конвертации арабских цифр в римские и наоборот.
 *  Все операнды и ответ будут принадлежать классу Number, чтобы можно было легко осуществлять перевод
 *  из арабских в римские и наоборот
 */
public class Number {
    private int value;
    private boolean isRoman;
    private String romanValue;

    /**
     * Конструктор, использующийся для операндов.
     * @param stringValue строковое значение операнда (может быть как римской, так и арабской цифрой)
     * @throws InvalidNumberFormat исключение выбрасывается, когда формат цифры некорректен
     */
    Number(String stringValue) throws InvalidNumberFormat {
        this.isRoman = false;
        // конвертируем строку в число для последующих вычислений
        this.value = this.convertStringToNumber(stringValue);
        // метод convertStringToNumber устанавливает переменную this.isRoman, если число оказалось римским
        if (this.isRoman) {
            // если римское, значит устанавливаем также и this.romanValue
            this.romanValue = stringValue;
        }
    }

    /**
     * Конструктор, использующийся для результата
     *
     * @param arabicValue арабское значение, полученное после вычислений
     * @param isRoman нужно ли конвертировать в римское число
     * @throws InvalidNumberFormat выбрасывается, если не получилось сконвертировать число в римское
     */
    Number(int arabicValue, boolean isRoman) throws InvalidNumberFormat {
        this.isRoman = isRoman;
        this.value = arabicValue;

        this.romanValue = this.arabicToRoman(arabicValue);
    }

    private int convertStringToNumber (String stringValue) throws InvalidNumberFormat {
        int value; // инициализируем как -1, чтобы потом проверить, был ли корректным формат ввода

        try {
            // пробуем преобразовать строку в число,
            // если она римская, то преобразовать не получится,
            // и мы попадём в блок catch
            value = Integer.parseInt(stringValue);


        } catch (NumberFormatException nfe) {
            this.isRoman = true;
            // если римская, нужно установить соответствие с арабской
            value = this.romanToArabic(stringValue);
        }

        if (value > 10 || value < 1) {
            throw new InvalidNumberFormat(stringValue.concat(DASH_DELIMITER).concat(VALUE_OUT_OF_RANGE));
        }

        return value;
    }

    /**
     * Метод конвертирования римского числа в арабское
     * @param input строка со значением римского числа
     * @return арабское целочисленное значение
     * @throws InvalidNumberFormat выбрасывается, если не удалось сконвертировать строку в арабское число
     */
    private int romanToArabic(String input) throws InvalidNumberFormat {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        // Получаем список, сортированный в обратном порядке, состоящий
        // из значений enum (RomanNumeral)
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        // пробегаемся по списку
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            // если строка начинается с имени символа из enum, значит
            // добавляем его к результату
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                // выкидываем цифру из input, чтобы перейти к следующему символу
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        // если в итоге осталось что-то в строке ввода, значит
        // полностью не удалось сконвертировать число и выбрасываем
        // исключение
        if (romanNumeral.length() > 0) {
            throw new InvalidNumberFormat(String.format(CANT_CONVERT_TO_ARABIC, input));
        }

        return result;
    }

    /**
     * Метод, выполняющий конвертацию арабского числа в римское
     * @param number целочисленное значение
     * @return строка с римским числом
     * @throws InvalidNumberFormat - выбрасывается, если не удалось сконвертировать римское число в арабское
     */
    private String arabicToRoman(int number) throws InvalidNumberFormat {
        if ((number <= 0) || (number > 4000)) {
            throw new InvalidNumberFormat(String.format(CANT_CONVERT_TO_ROMAN, String.valueOf(number)));
        }

        // получаем значения из Enum в виде сортированного в обратном порядке списка
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        // Пока мы не сделали введенное число равным нулю,
        // выполняем следующее:
        // Если значение арабское текущего символа меньше или равно введенного числа,
        // то добавляем к итоговой строчке римское представление этого числа.
        // Далее вычитаем из введенного числа значение из Enum.
        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public int value() {
        return this.value;
    }

    public boolean isRoman() {
        return this.isRoman;
    }

    public String romanValue() {
        return this.romanValue;
    }
}
