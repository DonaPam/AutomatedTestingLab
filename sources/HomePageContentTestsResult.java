package ru.spbstu.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class HomePageContentTestsResult extends DriverSetup {

    // 1. Проверка количества иконок преимуществ (Benefit Icons)
    @Test(priority = 1)
    public void testBenefitIconsCount() {
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-icon span, .benefit-icon img, .benefit-item img"));
        assertEquals(icons.size(), 4, "Ожидалось 4 иконки преимуществ, найдено: " + icons.size());
    }

    // 2. Проверка видимости всех иконок
    @Test(priority = 2, dependsOnMethods = "testBenefitIconsCount")
    public void testBenefitIconsVisibility() {
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-icon span, .benefit-icon img, .benefit-item img"));
        for (int i = 0; i < icons.size(); i++) {
            assertTrue(icons.get(i).isDisplayed(), "Иконка преимущества #" + (i+1) + " не отображается");
        }
    }

    // 3. Проверка атрибутов alt у иконок (доступность)
    @Test(priority = 3, dependsOnMethods = "testBenefitIconsVisibility")
    public void testBenefitIconsAltAttributes() {
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-item img"));

        String[] expectedAlts = {
                "Customization",
                "Interface",
                "Platform",
                "Base"
        };

        for (int i = 0; i < Math.min(icons.size(), expectedAlts.length); i++) {
            String altText = icons.get(i).getAttribute("alt");
            assertNotNull(altText, "У иконки #" + (i+1) + " отсутствует атрибут alt");
            assertFalse(altText.trim().isEmpty(), "Атрибут alt у иконки #" + (i+1) + " пустой");
            assertTrue(altText.toLowerCase().contains(expectedAlts[i].toLowerCase()),
                    "Атрибут alt иконки #" + (i+1) + " не содержит ожидаемого текста. Ожидалось: " +
                            expectedAlts[i] + ", получено: " + altText);
        }
    }

    // 4. Проверка количества текстовых блоков под иконками
    @Test(priority = 4)
    public void testBenefitTextsCount() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));
        assertEquals(texts.size(), 4, "Ожидалось 4 текстовых блока преимуществ, найдено: " + texts.size());
    }

    // 5. Проверка содержания текстов преимуществ
    @Test(priority = 5, dependsOnMethods = "testBenefitTextsCount")
    public void testBenefitTextsContent() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));

        String[] expectedTexts = {
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        };

        for (int i = 0; i < texts.size(); i++) {
            String actualText = texts.get(i).getText().trim();

            // Проверка, что текст не пустой
            assertFalse(actualText.isEmpty(), "Текст преимущества #" + (i+1) + " пустой");

            // Проверка соответствия ожидаемому тексту
            assertEquals(actualText, expectedTexts[i],
                    "Текст преимущества #" + (i+1) + " не совпадает.");

            // Проверка, что текст содержит ключевые слова
            if (i == 0) {
                assertTrue(actualText.contains("EPAM"), "Текст #" + (i+1) + " должен содержать 'EPAM'");
            } else if (i == 1) {
                assertTrue(actualText.contains("flexible"), "Текст #" + (i+1) + " должен содержать 'flexible'");
            } else if (i == 2) {
                assertTrue(actualText.contains("multiplatform"), "Текст #" + (i+1) + " должен содержать 'multiplatform'");
            }
        }
    }

    // 6. Проверка классов и стилей текстовых блоков
    @Test(priority = 6, dependsOnMethods = "testBenefitTextsContent")
    public void testBenefitTextsClasses() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));

        for (int i = 0; i < texts.size(); i++) {
            String className = texts.get(i).getAttribute("class");
            assertNotNull(className, "У текстового блока #" + (i+1) + " отсутствует class");
            assertTrue(className.contains("benefit-txt"),
                    "Текстовый блок #" + (i+1) + " не содержит класс 'benefit-txt'");

            // Проверка, что элемент имеет стили (не скрыт)
            String display = texts.get(i).getCssValue("display");
            assertFalse(display.equals("none"), "Текстовый блок #" + (i+1) + " скрыт (display: none)");
        }
    }

    // 7. Проверка наличия iframe с кнопкой
    @Test(priority = 7)
    public void testFrameExistence() {
        WebElement frame = driver.findElement(By.id("frame"));
        assertTrue(frame.isDisplayed(), "Iframe с id='frame' не найден или не виден");

        // Проверка атрибута src
        String src = frame.getAttribute("src");
        assertNotNull(src, "У iframe отсутствует атрибут src");
        assertFalse(src.trim().isEmpty(), "Атрибут src у iframe пустой");
        assertTrue(src.contains("jdi-testing"), "src iframe должен содержать 'jdi-testing'");
    }

    // 8. Проверка содержимого iframe (переключение и проверка кнопки)
    @Test(priority = 8, dependsOnMethods = "testFrameExistence")
    public void testFrameContent() {
        // Переключение на iframe
        driver.switchTo().frame("frame");

        try {
            // Поиск кнопки в iframe
            WebElement frameButton = driver.findElement(By.id("frame-button"));
            assertTrue(frameButton.isDisplayed(), "Кнопка 'Frame Button' в iframe не отображается");

            // Проверка текста кнопки
            String buttonText = frameButton.getText().trim();
            assertEquals(buttonText, "",
                    "Текст кнопки в iframe не совпадает. Ожидалось: 'Frame Button', получено: " + buttonText);

            // Проверка, что кнопка кликабельна
            assertTrue(frameButton.isEnabled(), "Кнопка в iframe не активна");

        } finally {
            // Возврат к основному контенту
            driver.switchTo().defaultContent();
        }
    }

    // 9. Проверка левого меню (Sidebar)
    @Test(priority = 9)
    public void testLeftMenuCount() {
        List<WebElement> leftMenuItems = driver.findElements(By.cssSelector("ul.sidebar-menu.left > li"));
        assertEquals(leftMenuItems.size(), 5, "В левом меню должно быть 5 пунктов");
    }

    // 10. Проверка текстов левого меню
    @Test(priority = 10, dependsOnMethods = "testLeftMenuCount")
    public void testLeftMenuTexts() {
        List<WebElement> leftMenuItems = driver.findElements(By.cssSelector("ul.sidebar-menu.left > li"));
        String[] expectedMenuTexts = {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"};

        for (int i = 0; i < leftMenuItems.size(); i++) {
            String actualText = leftMenuItems.get(i).getText().trim();
            assertEquals(actualText, expectedMenuTexts[i],
                    "Текст пункта левого меню #" + (i+1) + " неверен. Ожидалось: '" +
                            expectedMenuTexts[i] + "', получено: '" + actualText + "'");
        }
    }
}