package ru.spbstu.hsai;

import org.testng.annotations.Test;

public class HomePageContentTests extends DriverSetup {

    // 1. Проверка количества иконок преимуществ (Benefit Icons)
    @Test(priority = 1)
    public void testBenefitIconsCount() {
        // ЗАДАЧА: Проверить, что на странице отображается ровно 4 иконки преимуществ
        // ДЕЙСТВИЯ:
        //   - Найти все элементы иконок преимуществ, используя CSS-селектор
        //   - Подсчитать количество найденных элементов
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Количество иконок равно 4
    }

    // 2. Проверка видимости всех иконок
    @Test(priority = 2, dependsOnMethods = "testBenefitIconsCount")
    public void testBenefitIconsVisibility() {
        // ЗАДАЧА: Убедиться, что все иконки преимуществ отображаются на странице
        // ДЕЙСТВИЯ:
        //   - Найти все иконки преимуществ
        //   - Для каждой иконки проверить метод isDisplayed()
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Все 4 иконки имеют isDisplayed() == true
    }

    // 3. Проверка атрибутов alt у иконок (доступность)
    @Test(priority = 3, dependsOnMethods = "testBenefitIconsVisibility")
    public void testBenefitIconsAltAttributes() {
        // ЗАДАЧА: Проверить наличие и содержание атрибута alt у иконок (требование доступности)
        // ДЕЙСТВИЯ:
        //   - Найти все img-элементы внутри блоков преимуществ
        //   - Для каждой иконки получить атрибут "alt"
        //   - Сравнить с ожидаемыми значениями: "Customization", "Interface", "Platform", "Base"
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Атрибут alt не равен null и не пустой
        //   - Значение alt содержит ожидаемое ключевое слово (без учёта регистра)
    }

    // 4. Проверка количества текстовых блоков под иконками
    @Test(priority = 4)
    public void testBenefitTextsCount() {
        // ЗАДАЧА: Проверить, что под иконками преимуществ отображается ровно 4 текстовых блока
        // ДЕЙСТВИЯ:
        //   - Найти все элементы с классом "benefit-txt"
        //   - Подсчитать количество найденных элементов
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Количество текстовых блоков равно 4
    }

    // 5. Проверка содержания текстов преимуществ
    @Test(priority = 5, dependsOnMethods = "testBenefitTextsCount")
    public void testBenefitTextsContent() {
        // ЗАДАЧА: Проверить, что тексты под иконками соответствуют ожидаемому содержанию
        // ДЕЙСТВИЯ:
        //   - Найти все текстовые блоки с классом "benefit-txt"
        //   - Для каждого блока получить текст через getText().trim()
        //   - Сравнить с ожидаемыми значениями (учитывая переносы строк \n)
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Текст блока #1: "To include good practices\nand ideas from successful\nEPAM project"
        //   - Текст блока #2: "To be flexible and\ncustomizable"
        //   - Текст блока #3: "To be multiplatform"
        //   - Текст блока #4: "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        //   - Дополнительно: текст #1 содержит "EPAM", #2 содержит "flexible", #3 содержит "multiplatform"
    }

    // 6. Проверка классов и стилей текстовых блоков
    @Test(priority = 6, dependsOnMethods = "testBenefitTextsContent")
    public void testBenefitTextsClasses() {
        // ЗАДАЧА: Убедиться, что текстовые блоки имеют корректные классы и не скрыты
        // ДЕЙСТВИЯ:
        //   - Найти все элементы с классом "benefit-txt"
        //   - Для каждого элемента:
        //     * Получить атрибут class
        //     * Получить CSS-свойство display через getCssValue("display")
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Атрибут class не равен null и содержит "benefit-txt"
        //   - CSS-свойство display не равно "none" (элемент не скрыт)
    }

    // 7. Проверка наличия iframe с кнопкой
    @Test(priority = 7)
    public void testFrameExistence() {
        // ЗАДАЧА: Проверить наличие и видимость iframe на странице
        // ДЕЙСТВИЯ:
        //   - Найти iframe по id="frame"
        //   - Проверить, что элемент отображается (isDisplayed)
        //   - Получить атрибут src и проверить его содержание
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - iframe найден и отображается
        //   - Атрибут src не пустой и содержит "jdi-testing"
    }

    // 8. Проверка содержимого iframe (переключение и проверка кнопки)
    @Test(priority = 8, dependsOnMethods = "testFrameExistence")
    public void testFrameContent() {
        // ЗАДАЧА: Переключиться в iframe и проверить наличие кнопки внутри
        // ДЕЙСТВИЯ:
        //   - Переключиться на iframe: driver.switchTo().frame("frame")
        //   - Найти кнопку с id="frame-button"
        //   - Проверить видимость, текст и активность кнопки
        //   - Вернуться к основному контенту: driver.switchTo().defaultContent()
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Кнопка в iframe отображается
        //   - Текст кнопки: "Frame Button"
        //   - Кнопка активна (isEnabled == true)
    }

    // 9. Проверка количества элементов левого меню (Sidebar)
    @Test(priority = 9)
    public void testLeftMenuCount() {
        // ЗАДАЧА: Проверить, что в левом меню отображается ровно 5 пунктов
        // ДЕЙСТВИЯ:
        //   - Найти все li-элементы внутри ul.sidebar-menu.left
        //   - Подсчитать количество найденных элементов
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Количество пунктов меню равно 5
    }

    // 10. Проверка текстов левого меню
    @Test(priority = 10, dependsOnMethods = "testLeftMenuCount")
    public void testLeftMenuTexts() {
        // ЗАДАЧА: Проверить, что тексты пунктов левого меню соответствуют ожидаемым
        // ДЕЙСТВИЯ:
        //   - Найти все элементы левого меню
        //   - Для каждого элемента получить текст через getText().trim()
        //   - Сравнить с ожидаемыми значениями
        // ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
        //   - Пункт #1: "Home"
        //   - Пункт #2: "Contact form"
        //   - Пункт #3: "Service"
        //   - Пункт #4: "Metals & Colors"
        //   - Пункт #5: "Elements packs"
    }
}