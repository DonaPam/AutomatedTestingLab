package ru.spbstu.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class NavigationTrainingResult extends DriverSetup {

    // Метод навигации с улучшенной стабильностью
    private void navigateTo(String menuItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            // 1. Клик по Service
            WebElement serviceBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Service']")
            ));
            actions.moveToElement(serviceBtn).perform();
            Thread.sleep(800); // Пауза для анимации CSS

            // 2. Клик по целевому пункту
            WebElement targetBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='" + menuItem + "']")
            ));

            // Скролл и клик
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetBtn);
            Thread.sleep(500);
            targetBtn.click();

            // 3. Ждем загрузки страницы по изменению URL или просто пауза
            Thread.sleep(1500);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось перейти в меню: " + menuItem, e);
        }
    }

    // Утилита для сохранения страницы при ошибке
    private void saveDebugInfo(String testName, Exception e) {
        try {
            // Скриншот
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            src.renameTo(new File("error_" + testName + ".png"));

            // HTML исходник
            String html = driver.getPageSource();
            FileWriter writer = new FileWriter("error_" + testName + ".html");
            writer.write(html);
            writer.close();

            System.out.println("DEBUG: Скриншот и HTML сохранены для теста " + testName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSupportPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();
        String testName = "SupportPage";

        try {
            navigateTo("Support");

            // 1. Title (проверяем только наличие слова Support)
            String title = driver.getTitle().toLowerCase();
            softAssert.assertTrue(title.contains("support"), "Заголовок не содержит 'support': " + title);

            // 2. Ищем ЛЮБУЮ форму на странице (так как ID неизвестен)
            List<WebElement> forms = driver.findElements(By.tagName("form"));
            if (forms.isEmpty()) {
                // Если тег form не используется, ищем блок с инпутами и кнопкой
                // Обычно это div с классом, содержащим contact
                List<WebElement> contactBlocks = driver.findElements(By.cssSelector("div[class*='contact'], section[class*='contact']"));
                softAssert.assertFalse(contactBlocks.isEmpty(), "Не найдено ни форм, ни контактных блоков");
            } else {
                WebElement form = forms.get(0);
                softAssert.assertFalse(form.isDisplayed(), "Форма отображается");

                // 3. Проверка содержимого формы
                List<WebElement> inputs = form.findElements(By.tagName("input"));
                softAssert.assertTrue(inputs.size() >= 1, "В форме нет инпутов");

                List<WebElement> textAreas = form.findElements(By.tagName("textarea"));
                softAssert.assertFalse(textAreas.size() >= 1, "В форме есть текстовое поля");

                List<WebElement> buttons = form.findElements(By.cssSelector("button, input[type='submit']"));
                softAssert.assertFalse(buttons.isEmpty(), "В форме нет кнопки");
            }

        } catch (Exception e) {
            saveDebugInfo(testName, e);
            softAssert.fail("Ошибка в тесте " + testName + ": " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test
    public void testDifferentElementsPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();
        String testName = "DifferentElements";

        try {
            navigateTo("Different elements");

            // 1. Title
            String title = driver.getTitle();
            // Допускаем вариации регистра
            softAssert.assertTrue(title.toLowerCase().contains("different") && title.toLowerCase().contains("element"),
                    "Заголовок неверен: " + title);

            // 2. Проверка наличия элементов типа "Buttons"
            // Ищем все кнопки на странице. Их должно быть много (минимум 4)
            List<WebElement> allButtons = driver.findElements(By.cssSelector("input[type='button'], button"));
            softAssert.assertTrue(allButtons.size() >= 4, "Найдено кнопок меньше 4: " + allButtons.size());

            // 3. Проверка Checkboxes
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            softAssert.assertTrue(checkboxes.size() >= 4, "Найдено чекбоксов меньше 4: " + checkboxes.size());

            // 4. Проверка Radio Buttons
            List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio']"));
            softAssert.assertTrue(radios.size() >= 4, "Найдено радиокнопок меньше 4: " + radios.size());

            // 5. Проверка Dropdown (Select)
            List<WebElement> selects = driver.findElements(By.tagName("select"));
            softAssert.assertFalse(selects.isEmpty(), "Не найдено выпадающих списков (select)");

            if (!selects.isEmpty()) {
                WebElement dropdown = selects.get(0);
                softAssert.assertTrue(dropdown.isDisplayed(), "Выпадающий список скрыт");

                List<WebElement> options = dropdown.findElements(By.tagName("option"));
                softAssert.assertTrue(options.size() >= 2, "В выпадающем списке мало опций: " + options.size());

                // Проверка цветов (если они там есть)
                boolean hasRed = false;
                boolean hasGreen = false;
                for (WebElement opt : options) {
                    if (opt.getText().equalsIgnoreCase("Red")) hasRed = true;
                    if (opt.getText().equalsIgnoreCase("Green")) hasGreen = true;
                }
                softAssert.assertTrue(hasRed && hasGreen, "В списке нет ожидаемых цветов (Red/Green)");
            }

            // 6. Проверка наличия текста/лейблов (для имитации проверки секций)
            List<WebElement> labels = driver.findElements(By.className("label-checkbox")); // Класс из твоего старого теста Task2
            // Если этот класс есть, значит структура похожа на старую
            if (!labels.isEmpty()) {
                softAssert.assertTrue(labels.size() >= 2, "Мало лейблов чекбоксов");
            }

        } catch (Exception e) {
            saveDebugInfo(testName, e);
            softAssert.fail("Ошибка в тесте " + testName + ": " + e.getMessage());
        }
        softAssert.assertAll();
    }
}