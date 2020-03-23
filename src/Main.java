import exceptions.InvalidInputFormat;
import exceptions.InvalidNumberFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static constants.Strings.ENTER_EXPRESSION;

public class Main {
    public static void main(String[] args) {
        try
        {
            System.out.println(ENTER_EXPRESSION);
            // создаём BufferedReader для чтения строки из консоли
            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            String str;
            // читаем строчку из консоли
            str = obj.readLine();
            try {
                // создаём экземпляр класса Calculator, указывая в качестве параметра конструктора
                // полученную строку
                Calculator calc = new Calculator(str);
                // выводим результат, вызывая метод calculate()
                System.out.println(calc.calculate());
            }
            catch (InvalidNumberFormat | InvalidInputFormat inf) {
                // Если поймали исключение (неверный формат ввода или неверная цифра/число), то заканчиваем выполнение
                // программы и выводим сообщение об ошибке
                System.out.println(inf.getMessage());
            }
        } catch (IOException e) {
            // обработка исключения ввода-вывода
            System.out.println(e.getMessage());
        }

    }
}
