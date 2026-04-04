# Методичка: Файлы конфигурации Selenium + TestNG

---

## 📁 Структура проекта

```
Lab4Testing/
├── pom.xml                          # [КОРЕНЬ] Конфигурация сборки Maven
└── src/test/
    ├── java/ru/spbstu/hsai/
    │   ├── DriverSetup.java         # [JAVA] Базовый класс: инициализация драйвера
    │   └── *.java                   # [JAVA] Остальные тестовые классы
    └── resources/
        └── suite-for-hw2.xml        # [RESOURCES] Конфигурация запуска тестов TestNG
```

> ⚠️ **Важно**: Соблюдайте путь `ru/spbstu/hsai/` — это соответствие `package` в Java-файлах.


---

## 1️⃣ `src/test/resources/suite-for-hw2.xml`

```xml
<!-- Подключение схемы TestNG для валидации XML -->
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<!-- Имя набора тестов (отображается в отчётах) -->
<suite name="suite-for-hw2">
    <!-- Логическая группа тестов внутри suite -->
    <test name="WebDriver tests">
        <!-- Запуск всех тестовых классов в указанном пакете -->
        <packages>
            <package name="ru.spbstu.hsai"/>
        </packages>
    </test>
</suite>
```

---

## 2️⃣ `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Координаты проекта в репозитории -->
    <groupId>ru.spbstu.hsai</groupId>
    <artifactId>Lab4Testing</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>23</maven.compiler.source>
        <maven.compiler.target>23</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <!-- Версии фреймворков для удобного управления -->
        <junit.jupiter.version>5.8.1</junit.jupiter.version>
        <junit.platform.version>1.8.1</junit.platform.version>
    </properties>

    <profiles>
        <!-- Профиль для гибкого указания пути к suite-файлу -->
        <profile>
            <id>suite-for-hw2</id>
            <properties>
                <testng.suite.file>${project.build.testOutputDirectory}/suite-for-hw2.xml</testng.suite.file>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
    </profiles>

    <dependencies>
        <!-- Зависимости для JUnit 5, чтобы оттуда можно было дёргать методы -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>${junit.platform.version}</version>
            <scope>test</scope>
        </dependency>
        
        <!-- фреймворк TestNG для UI-тестов -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
            <scope>test</scope>
        </dependency>
        
        <!-- Библиотека Selenium: автоматизация браузера (в новых версиях не требуется ставить chromedriver) -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.15.0</version>
        </dependency>
    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                    <configuration>
                        <source>23</source>
                        <target>23</target>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

---

## 3️⃣ `src/test/java/ru/spbstu/hsai/DriverSetup.java`

```java
package ru.spbstu.hsai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DriverSetup {

    // Общий экземпляр драйвера для всех тестовых классов пакета
    protected static WebDriver driver;

    // Выполняется один раз перед всеми тестами в suite
    // Требование TestNG: метод должен быть public static
    @BeforeTest
    public static void setup() {
        // Запуск браузера (Selenium Manager сам скачает драйвер)
        driver = new ChromeDriver();

        // 1. Open test site by URL
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");

        // 3. Perform login
        driver.findElement(By.cssSelector("html > body > header > div > nav > ul.uui-navigation.navbar-nav.navbar-right > li > a > span")).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();
    }

    // Выполняется после всех тестов: освобождение ресурсов
    // Требование TestNG: метод должен быть public static
    @AfterTest
    public static void exit() {
        //10. Close Browser
        driver.close();
    }
}
```

---

## ✅ Запуск

```bash
mvn clean test
```

Отчёты: `target/surefire-reports/index.html`
