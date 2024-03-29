/**
 * Создаем класс для вычисления даты: текущая дата - 1 сутки
* */
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;

public class LocalDate {
    //public static void main(String[] args) {
      java.time.LocalDate current_date = now().plusDays( +30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String s_current_date = current_date.format(formatter);


        //System.out.println(s_current_date);
    //}
}
