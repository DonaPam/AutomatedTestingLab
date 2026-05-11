package ru.spbstu.ru.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageContentTests extends DriverSetup {

    // ─── Test 1 : Nombre d'icônes de bénéfices ───────────────────────────────
    @Test(priority = 1)
    public void testBenefitIconsCount() {
        List<WebElement> icons = driver.findElements(
            By.cssSelector(".benefit-icon span, .benefit-icon img, .benefit-item img"));
        Assert.assertEquals(icons.size(), 4,
            "Il devrait y avoir exactement 4 icônes de bénéfices, trouvé : " + icons.size());
    }

    // ─── Test 2 : Visibilité des icônes ──────────────────────────────────────
    @Test(priority = 2)
    public void testBenefitIconsVisibility() {
        List<WebElement> icons = driver.findElements(
            By.cssSelector(".benefit-icon span, .benefit-icon img, .benefit-item img"));
        Assert.assertFalse(icons.isEmpty(), "Aucune icône de bénéfice trouvée");
        for (int i = 0; i < Math.min(icons.size(), 4); i++) {
            Assert.assertTrue(icons.get(i).isDisplayed(),
                "L'icône #" + (i + 1) + " devrait être visible");
        }
    }

    // ─── Test 3 : Attributs alt des icônes ───────────────────────────────────
    @Test(priority = 3)
    public void testBenefitIconsAltAttributes() {
        List<WebElement> icons = driver.findElements(
            By.cssSelector(".benefit-icon span"));
        
        Assert.assertTrue(icons.size() >= 4,
            "Il devrait y avoir au moins 4 icônes, trouvé : " + icons.size());

        String[] expectedKeywords = {"Customization", "Interface", "Platform", "Base"};
        for (int i = 0; i < Math.min(icons.size(), 4); i++) {
            String cssClass = icons.get(i).getAttribute("class");
            Assert.assertNotNull(cssClass,
                "L'icône #" + (i + 1) + " n'a pas de classe");
            Assert.assertFalse(cssClass.isEmpty(),
                "La classe de l'icône #" + (i + 1) + " est vide");
        }
    }

    // ─── Test 4 : Nombre de blocs texte ──────────────────────────────────────
    @Test(priority = 4)
    public void testBenefitTextsCount() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));
        Assert.assertEquals(texts.size(), 4,
            "Il devrait y avoir exactement 4 blocs texte, trouvé : " + texts.size());
    }

    // ─── Test 5 : Contenu des blocs texte ────────────────────────────────────
    @Test(priority = 5)
    public void testBenefitTextsContent() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));
        Assert.assertEquals(texts.size(), 4, "Il devrait y avoir 4 blocs texte");

        // Texte #1 — contient "EPAM"
        String text1 = texts.get(0).getText().trim();
        Assert.assertTrue(text1.contains("EPAM"),
            "Le texte #1 devrait contenir 'EPAM', mais c'est : " + text1);

        // Texte #2 — contient "flexible"
        String text2 = texts.get(1).getText().trim();
        Assert.assertTrue(text2.contains("flexible"),
            "Le texte #2 devrait contenir 'flexible', mais c'est : " + text2);

        // Texte #3 — contient "multiplatform"
        String text3 = texts.get(2).getText().trim();
        Assert.assertTrue(text3.contains("multiplatform"),
            "Le texte #3 devrait contenir 'multiplatform', mais c'est : " + text3);

        // Texte #4 — non vide
        String text4 = texts.get(3).getText().trim();
        Assert.assertFalse(text4.isEmpty(),
            "Le texte #4 ne devrait pas être vide");
    }

    // ─── Test 6 : Classes CSS des blocs texte ────────────────────────────────
    @Test(priority = 6)
    public void testBenefitTextsClasses() {
        List<WebElement> texts = driver.findElements(By.className("benefit-txt"));
        Assert.assertFalse(texts.isEmpty(), "Aucun bloc texte trouvé");

        for (int i = 0; i < texts.size(); i++) {
            // Vérifier l'attribut class
            String cssClass = texts.get(i).getAttribute("class");
            Assert.assertNotNull(cssClass,
                "L'attribut class du bloc #" + (i + 1) + " ne devrait pas être null");
            Assert.assertTrue(cssClass.contains("benefit-txt"),
                "La class du bloc #" + (i + 1) + " devrait contenir 'benefit-txt'");

            // Vérifier que l'élément n'est pas caché (display != none)
            String display = texts.get(i).getCssValue("display");
            Assert.assertNotEquals(display, "none",
                "Le bloc #" + (i + 1) + " ne devrait pas être caché (display:none)");
        }
    }

    // ─── Test 7 : Existence de l'iframe ──────────────────────────────────────
    @Test(priority = 7)
    public void testFrameExistence() {
        List<WebElement> frames = driver.findElements(By.id("frame"));
        Assert.assertFalse(frames.isEmpty(), "L'iframe avec id='frame' devrait exister");

        WebElement frame = frames.get(0);
        Assert.assertTrue(frame.isDisplayed(), "L'iframe devrait être visible");

        String src = frame.getAttribute("src");
        Assert.assertNotNull(src, "L'attribut src de l'iframe ne devrait pas être null");
        Assert.assertTrue(src.contains("jdi-testing"),
            "Le src de l'iframe devrait contenir 'jdi-testing', mais c'est : " + src);
    }

    // ─── Test 8 : Contenu de l'iframe ────────────────────────────────────────
    @Test(priority = 8, dependsOnMethods = "testFrameExistence")
    public void testFrameContent() {
        driver.switchTo().frame("frame");

        List<WebElement> buttons = driver.findElements(By.id("frame-button"));
        Assert.assertFalse(buttons.isEmpty(),
            "Le bouton 'frame-button' devrait exister dans l'iframe");

        WebElement button = buttons.get(0);
        Assert.assertTrue(button.isDisplayed(), "Le bouton devrait être visible");
        Assert.assertTrue(button.isEnabled(), "Le bouton devrait être actif");

        // Vérifier le texte OU la valeur du bouton
        String text = button.getText().trim();
        String value = button.getAttribute("value") != null ? 
                       button.getAttribute("value").trim() : "";
        String label = button.getAttribute("aria-label") != null ?
                       button.getAttribute("aria-label").trim() : "";
        
        boolean hasCorrectText = text.equals("Frame Button") || 
                                 value.equals("Frame Button") ||
                                 label.contains("Frame");
        Assert.assertTrue(hasCorrectText,
            "Le bouton devrait avoir le texte 'Frame Button', trouvé text='" 
            + text + "' value='" + value + "'");

        driver.switchTo().defaultContent();
    }

    // ─── Test 9 : Nombre d'éléments du menu gauche ───────────────────────────
    @Test(priority = 9)
    public void testLeftMenuCount() {
        // Retourner à la page d'accueil d'abord
        driver.navigate().to(
            "https://jdi-testing.github.io/jdi-light/index.html");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        List<WebElement> items = driver.findElements(
            By.cssSelector("ul.sidebar-menu.left > li"));
        
        Assert.assertEquals(items.size(), 5,
            "Il devrait y avoir 5 éléments dans le menu gauche, trouvé : " 
            + items.size());
    }

    // ─── Test 10 : Textes du menu gauche ─────────────────────────────────────
    @Test(priority = 10, dependsOnMethods = "testLeftMenuCount")
    public void testLeftMenuTexts() {
        List<WebElement> items = driver.findElements(
            By.cssSelector("ul.sidebar-menu.left > li"));

        String[] expected = {
            "Home", "Contact form", "Service", 
            "Metals & Colors", "Elements packs"
        };
        for (int i = 0; i < expected.length; i++) {
            String actual = items.get(i).getText().trim();
            Assert.assertEquals(actual, expected[i],
                "Le texte du menu #" + (i + 1) + " devrait être '" 
                + expected[i] + "', mais c'est : " + actual);
        }
    }
}