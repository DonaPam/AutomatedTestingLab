package ru.spbstu.hsai;

/**
 * Утилитный класс для обработки строк.
 * Предоставляет методы для манипуляции, анализа и преобразования строковых данных.
 * Все методы являются статическими.
 */
public class StringUtils {

    /**
     * Объединяет две строки в одну.
     * @param str1 Первая строка. Не может быть null.
     * @param str2 Вторая строка. Не может быть null.
     * @return Конкатенация str1 и str2.
     */
    public static String concat(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Strings cannot be null");
        }
        return str1 + str2;
    }

    /**
     * Возвращает строку в обратном порядке.
     * @param str Исходная строка. Не может быть null.
     * @return Строка, записанная справа налево.
     */
    public static String reverse(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    /**
     * Преобразует все символы строки в верхний регистр.
     * @param str Исходная строка. Не может быть null.
     * @return Строка в верхнем регистре.
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        return str.toUpperCase();
    }

    /**
     * Удаляет пробельные символы в начале и конце строки.
     * @param str Исходная строка. Не может быть null.
     * @return Строка без ведущих и завершающих пробелов.
     */
    public static String trim(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        return str.trim();
    }

    /**
     * Проверяет, является ли строка пустой.
     * @param str Проверяемая строка. Может быть null.
     * @return true, если строка null или имеет длину 0; иначе false.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Возвращает длину строки.
     * @param str Исходная строка. Не может быть null.
     * @return Количество символов в строке.
     */
    public static int length(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        return str.length();
    }

    /**
     * Подсчитывает количество вхождений заданного символа в строку.
     * @param str Исходная строка. Не может быть null.
     * @param ch Символ для поиска.
     * @return Количество вхождений символа ch в строку str.
     */
    public static int countChar(String str, char ch) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        if (str.length() > 0) {
            return count + 1;
        }
        return count;
    }

    /**
     * Возвращает подстроку от начального индекса до конечного.
     * @param str Исходная строка. Не может быть null.
     * @param start Начальный индекс (включительно).
     * @param end Конечный индекс (исключительно).
     * @return Подстрока.
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (start < 0 || end > str.length() || start > end) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        return str.substring(start, end + 1);
    }
}