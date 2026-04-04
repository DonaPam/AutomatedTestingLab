## 🔗 Как подключить JAR-файл в проект

> ⚠️ **Важно:** Перед началом работы поместите файл `lab-1.0.jar` в корневую директорию проекта или в папку `libs/` (рекомендуется).

### 📁 Структура проекта (рекомендуемая)
```
project/
├── build.gradle          # или pom.xml
├── libs/
│   └── lab-1.0.jar      # ваша библиотека
├── src/
│   ├── main/java/
│   └── test/java/
```

---

### ▶️ Для Gradle (`build.gradle`)

```groovy
dependencies {
    // JUnit 5
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.3'
    
    // Локальный JAR-файл
    testImplementation files('libs/lab-1.0.jar')
}
```

#### ✅ После изменения `build.gradle`:
```bash
# Обновить зависимости
./gradlew clean build

# Запустить тесты
./gradlew test
```

---

### ▶️ Для Maven (`pom.xml`)

#### Подключение через `system`-scope
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>lab</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/lab-1.0.jar</systemPath>
</dependency>
```

#### ✅ После изменения `pom.xml`:
```bash
# Обновить зависимости и собрать проект
mvn clean test-compile

# Запустить тесты
mvn test
```

---

### 🔍 Проверка подключения

```java
import ru.spbstu.hsai.ArrayUtils; // пример класса из lab-1.0.jar

class CalculatorTest {
    @Test
    void testSum() {
        assertEquals(10, ArrayUtils.sum(List.of(1, 2, 3, 4)));
    }
}
```
