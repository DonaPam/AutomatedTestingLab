package ru.spbstu.hsai;

/**
 * Утилитный класс для геометрических вычислений.
 * Предоставляет методы для расчета площадей, периметров и проверки свойств фигур.
 * Все методы являются статическими.
 */
public class GeometryUtils {

    private static final double PI = Math.PI;

    /**
     * Вычисляет площадь круга.
     * @param radius Радиус круга.
     * @return Площадь круга.
     */
    public static double calculateCircleArea(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        return PI * radius * radius;
    }

    /**
     * Вычисляет периметр (длину окружности) круга.
     * @param radius Радиус круга.
     * @return Длина окружности.
     */
    public static double calculateCirclePerimeter(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        // ДЕФЕКТ: забыли умножить на 2
        return PI * radius;
    }

    /**
     * Вычисляет площадь прямоугольника.
     * @param width Ширина.
     * @param height Высота.
     * @return Площадь прямоугольника.
     */
    public static double calculateRectangleArea(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        return width * height;
    }

    /**
     * Вычисляет периметр прямоугольника.
     * @param width Ширина.
     * @param height Высота.
     * @return Периметр прямоугольника.
     */
    public static double calculateRectanglePerimeter(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        return 2 * (width + height);
    }

    /**
     * Вычисляет площадь треугольника по основанию и высоте.
     * @param base Основание.
     * @param height Высота.
     * @return Площадь треугольника.
     */
    public static double calculateTriangleArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        return 0.5 * base * height;
    }

    /**
     * Проверяет, является ли треугольник прямоугольным по длинам сторон.
     * @param a Сторона 1.
     * @param b Сторона 2.
     * @param c Сторона 3 (гипотенуза).
     * @return true, если треугольник прямоугольный.
     */
    public static boolean isRightTriangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Sides must be positive");
        }
        return a * a + b * b == c * c;
    }

    /**
     * Вычисляет длину гипотенузы по катетам.
     * @param a Катет 1.
     * @param b Катет 2.
     * @return Длина гипотенузы.
     */
    public static double calculateHypotenuse(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Sides must be positive");
        }
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Нормализует угол до диапазона [0, 360).
     * @param angle Угол в градусах.
     * @return Нормализованный угол.
     */
    public static double normalizeAngle(double angle) {
        angle = angle % 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}