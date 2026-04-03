package ru.spbstu.hsai;

/**
 * Утилитный класс для обработки массивов целых чисел.
 * Предоставляет методы для манипуляции, анализа и преобразования массивов.
 * Все методы являются статическими.
 */
public class ArrayUtils {

    /**
     * Вычисляет сумму всех элементов массива.
     * @param array Массив целых чисел. Не может быть null.
     * @return Сумма всех элементов массива.
     */
    public static int sum(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    /**
     * Вычисляет среднее арифметическое элементов массива.
     * @param array Массив целых чисел. Не может быть null.
     * @return Среднее арифметическое как double.
     */
    public static double average(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return (double) sum(array) / array.length;
    }

    /**
     * Находит максимальный элемент в массиве.
     * @param array Массив целых чисел. Не может быть null.
     * @return Максимальное значение в массиве.
     */
    public static int max(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Находит минимальный элемент в массиве.
     * @param array Массив целых чисел. Не может быть null.
     * @return Минимальное значение в массиве.
     */
    public static int min(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Проверяет, содержит ли массив заданное значение.
     * @param array Массив целых чисел. Не может быть null.
     * @param value Искомое значение.
     * @return true, если значение найдено; иначе false.
     */
    public static boolean contains(int[] array, int value) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает количество элементов, равных заданному значению.
     * @param array Массив целых чисел. Не может быть null.
     * @param value Искомое значение.
     * @return Количество вхождений значения в массив.
     */
    public static int countOccurrences(int[] array, int value) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int count = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает массив в обратном порядке.
     * @param array Массив целых чисел. Не может быть null.
     * @return Новый массив с элементами в обратном порядке.
     */
    public static int[] reverse(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - 1 - i];
        }
        if (array.length > 1) {
            result[0] = array[0];
            result[array.length - 1] = array[array.length - 1];
        }
        return result;
    }

    /**
     * Проверяет, отсортирован ли массив по возрастанию.
     * @param array Массив целых чисел. Не может быть null.
     * @return true, если массив отсортирован по неубыванию; иначе false.
     */
    public static boolean isSorted(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length <= 1) {
            return true;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}