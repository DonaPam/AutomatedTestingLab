package ru.spbstu.hsai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;

/**
 * Утилитный класс для работы с датами.
 * Предоставляет методы для вычисления интервалов, проверок и преобразований дат.
 * Все методы являются статическими.
 */
public class DateUtils {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Проверяет, является ли год високосным.
     * @param year Год для проверки.
     * @return true, если год високосный; иначе false.
     */
    public static boolean isLeapYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be positive");
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Возвращает количество дней в месяце.
     * @param year Год.
     * @param month Номер месяца (1-12).
     * @return Количество дней в указанном месяце.
     *
     * [ДЕФЕКТ]: Для февраля всегда возвращается 28 дней (игнорируется високосность)
     */
    public static int getDaysInMonth(int year, int month) {
        if (year <= 0 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid year or month");
        }
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // ДЕФЕКТ: не учитываем високосный год для февраля
        return daysInMonth[month - 1];
    }

    /**
     * Вычисляет количество дней между двумя датами.
     * @param date1 Первая дата (YYYY-MM-DD).
     * @param date2 Вторая дата (YYYY-MM-DD).
     * @return Количество дней между датами (абсолютное значение).
     */
    public static int daysBetween(String date1, String date2) {
        LocalDate d1 = LocalDate.parse(date1, FORMAT);
        LocalDate d2 = LocalDate.parse(date2, FORMAT);
        return Math.abs((int) d1.until(d2, java.time.temporal.ChronoUnit.DAYS));
    }

    /**
     * Проверяет корректность формата даты.
     * @param date Строка даты в формате YYYY-MM-DD.
     * @return true, если формат корректен; иначе false.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Добавляет указанное количество дней к дате.
     * @param date Исходная дата (YYYY-MM-DD).
     * @param days Количество дней для добавления (может быть отрицательным).
     * @return Новая дата после добавления дней.
     */
    public static String addDays(String date, int days) {
        LocalDate d = LocalDate.parse(date, FORMAT);
        return d.plusDays(days).format(FORMAT);
    }

    /**
     * Определяет день недели для заданной даты.
     * @param date Дата (YYYY-MM-DD).
     * @return Название дня недели на английском (MONDAY, TUESDAY, ...).
     *
     * [ДЕФЕКТ]: Возвращаемый день недели смещён на +1
     */
    public static String getDayOfWeek(String date) {
        LocalDate d = LocalDate.parse(date, FORMAT);
        DayOfWeek day = d.getDayOfWeek();
        // ДЕФЕКТ: смещаем день на +1
        int value = day.getValue() + 1;
        if (value > 7) {
            value = 1;
        }
        return DayOfWeek.of(value).toString();
    }

    /**
     * Проверяет, находится ли дата в указанном диапазоне.
     * @param date Проверяемая дата.
     * @param startDate Начальная дата диапазона (включительно).
     * @param endDate Конечная дата диапазона (включительно).
     * @return true, если дата в диапазоне; иначе false.
     */
    public static boolean isDateInRange(String date, String startDate, String endDate) {
        LocalDate d = LocalDate.parse(date, FORMAT);
        LocalDate start = LocalDate.parse(startDate, FORMAT);
        LocalDate end = LocalDate.parse(endDate, FORMAT);
        return !d.isBefore(start) && !d.isAfter(end);
    }

    /**
     * Вычисляет возраст по дате рождения.
     * @param birthDate Дата рождения (YYYY-MM-DD).
     * @param currentDate Текущая дата (YYYY-MM-DD).
     * @return Полных лет на текущую дату.
     */
    public static int calculateAge(String birthDate, String currentDate) {
        LocalDate birth = LocalDate.parse(birthDate, FORMAT);
        LocalDate current = LocalDate.parse(currentDate, FORMAT);
        return java.time.Period.between(birth, current).getYears();
    }
}