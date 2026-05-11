package ru.spbstu.ru.hsai;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void initAll() {
        System.out.println("=== Initialisation : lancement des tests ArrayUtils ===");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== Fin : tous les tests ArrayUtils sont terminés ===");
    }
}