package ru.spbstu.ru.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class NavigationTraining1 extends DriverSetup {

    @Test
    public void testSupportPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Navigation directe vers la page contacts
        driver.navigate().to(
            "https://jdi-testing.github.io/jdi-light/contacts.html"
        );
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // 2. Vérifier que le titre contient "contact"
        String title = driver.getTitle().toLowerCase();
        softAssert.assertTrue(
            title.contains("support") || title.contains("contact"),
            "Le titre ne contient ni 'support' ni 'contact' : " + title
        );

        // 3. Chercher formulaire OU bloc contact
        List<WebElement> forms = driver.findElements(By.tagName("form"));
        List<WebElement> contactBlocks = driver.findElements(
            By.cssSelector("div[class*='contact'], section[class*='contact']"));
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
        List<WebElement> buttons = driver.findElements(
            By.cssSelector("button, input[type='submit']"));

        // 4. Au moins un formulaire OU bloc contact OU inputs présents
        boolean hasForm = !forms.isEmpty();
        boolean hasContact = !contactBlocks.isEmpty();
        boolean hasInputs = !inputs.isEmpty();

        softAssert.assertTrue(hasForm || hasContact || hasInputs,
            "Aucun formulaire, bloc contact ou input trouvé");

     // 5. Vérifier visibilité du premier input VISIBLE trouvé
        WebElement visibleInput = null;
        for (WebElement input : inputs) {
            if (input.isDisplayed()) {
                visibleInput = input;
                break;
            }
        }
        softAssert.assertNotNull(visibleInput,
            "Il devrait y avoir au moins 1 input visible sur la page");
        
        // 6. Vérifier présence d'inputs
        softAssert.assertFalse(inputs.isEmpty(),
            "Il devrait y avoir au moins 1 input sur la page");

        // 7. Vérifier textarea OU plusieurs inputs
        boolean hasTextarea = !textareas.isEmpty();
        boolean hasMultipleInputs = inputs.size() >= 2;
        softAssert.assertTrue(hasTextarea || hasMultipleInputs,
            "Il devrait y avoir un textarea ou plusieurs inputs");

        // 8. Vérifier bouton de soumission
        softAssert.assertFalse(buttons.isEmpty(),
            "Il devrait y avoir au moins un bouton sur la page");

        softAssert.assertAll();
    }

    @Test
    public void testDifferentElementsPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Navigation directe vers Different Elements
        driver.navigate().to(
            "https://jdi-testing.github.io/jdi-light/different-elements.html"
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[type='checkbox']")
        ));

        // 2. Vérifier le titre
        String title = driver.getTitle().toLowerCase();
        softAssert.assertTrue(title.contains("different"),
            "Le titre devrait contenir 'different' : " + title);

        // 3. Vérifier au moins 4 checkboxes
        List<WebElement> checkboxes = driver.findElements(
            By.cssSelector("input[type='checkbox']"));
        softAssert.assertTrue(checkboxes.size() >= 4,
            "Il devrait y avoir au moins 4 checkboxes, trouvé : " + checkboxes.size());

        // 4. Tous les checkboxes sont visibles
        for (int i = 0; i < checkboxes.size(); i++) {
            softAssert.assertTrue(checkboxes.get(i).isDisplayed(),
                "Checkbox #" + (i + 1) + " n'est pas visible");
        }

        // 5. Vérifier au moins 4 radio buttons
        List<WebElement> radios = driver.findElements(
            By.cssSelector("input[type='radio']"));
        softAssert.assertTrue(radios.size() >= 4,
            "Il devrait y avoir au moins 4 radio buttons, trouvé : " + radios.size());

        // 6. Vérifier la présence d'un dropdown
        List<WebElement> selects = driver.findElements(By.tagName("select"));
        softAssert.assertFalse(selects.isEmpty(), "Aucun dropdown trouvé");

        if (!selects.isEmpty()) {
            softAssert.assertTrue(selects.get(0).isDisplayed(),
                "Le dropdown devrait être visible");

            List<WebElement> options = selects.get(0)
                .findElements(By.tagName("option"));
            softAssert.assertTrue(options.size() >= 2,
                "Le dropdown devrait avoir au moins 2 options, trouvé : " 
                + options.size());
        }

        // 7. Vérifier au moins 2 boutons
        List<WebElement> buttons = driver.findElements(
            By.cssSelector("input[type='button'], button"));
        softAssert.assertTrue(buttons.size() >= 2,
            "Il devrait y avoir au moins 2 boutons, trouvé : " + buttons.size());

        softAssert.assertAll();
    }
}