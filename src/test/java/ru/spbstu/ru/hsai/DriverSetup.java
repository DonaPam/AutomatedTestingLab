package ru.spbstu.ru.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class DriverSetup {

    protected static WebDriver driver;

    @BeforeTest
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(
            "https://jdi-testing.github.io/jdi-light/index.html"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Cliquer sur l'icône user
        wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("ul.uui-navigation.navbar-right > li > a > span")
        )).click();

        // Attendre le champ name
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).sendKeys("Jdi1234");

        // Cliquer via JavaScript pour éviter l'interception
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        // Attendre que le login soit confirmé
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
    }

    @AfterTest
    public static void exit() {
        driver.close();
    }
}