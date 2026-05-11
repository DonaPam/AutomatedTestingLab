package ru.spbstu.ru.hsai;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.spbstu.hsai.ArrayUtils;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour ArrayUtils")
public class ArrayUtilsTest extends BaseTest {

    private int[] sampleArray;

    @BeforeEach
    void setUp() {
        sampleArray = new int[]{3, 1, 4, 1, 5};
        System.out.println("[setUp] Préparation des données");
    }

    @AfterEach
    void tearDown() {
        sampleArray = null;
        System.out.println("[tearDown] Nettoyage des données");
    }

    // ─── 1. Tests pour sum(int[]) ─────────────────────────────────────────────

    @Test
    @DisplayName("sum : null => IllegalArgumentException")
    void sum_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> ArrayUtils.sum(null));
    }

    @Test
    @DisplayName("sum : tableau vide => 0")
    void sum_EmptyArray_ReturnsZero() {
        assertEquals(0, ArrayUtils.sum(new int[]{}));
    }

    @Test
    @DisplayName("sum : un seul élément")
    void sum_SingleElement_ReturnsSameValue() {
        assertEquals(42, ArrayUtils.sum(new int[]{42}));
    }

    @ParameterizedTest(name = "sum([{0},{1}]) = {2}")
    @CsvSource({
        "2, 5, 7",
        "-3, -7, -10",
        "10, -4, 6",
        "0, 99, 99",
        "0, 0, 0"
    })
    @DisplayName("sum : classes d'équivalence pour deux éléments")
    void sum_TwoElements_ReturnsCorrectResult(int a, int b, int expected) {
        assertEquals(expected, ArrayUtils.sum(new int[]{a, b}));
    }

    @Test
    @DisplayName("sum : valeur limite Integer.MAX_VALUE")
    void sum_MaxIntValue_ReturnsCorrectResult() {
        assertEquals(Integer.MAX_VALUE,
            ArrayUtils.sum(new int[]{Integer.MAX_VALUE}));
    }

    @Test
    @DisplayName("sum : valeur limite Integer.MIN_VALUE")
    void sum_MinIntValue_ReturnsCorrectResult() {
        assertEquals(Integer.MIN_VALUE,
            ArrayUtils.sum(new int[]{Integer.MIN_VALUE}));
    }

     // ─── 2. Tests pour average(int[]) ────────────────────────────────────────────

    @Test
    @DisplayName("average : null => IllegalArgumentException")
    void average_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> ArrayUtils.average(null));
    }

    @Test
    @DisplayName("average : tableau vide => IllegalArgumentException")
    void average_EmptyArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> ArrayUtils.average(new int[]{}));
    }

    @Test
    @DisplayName("average : un seul élément")
    void average_SingleElement_ReturnsCorrectValue() {
        assertEquals(5.0, ArrayUtils.average(new int[]{5}), 0.0001);
    }

    @ParameterizedTest(name = "average([{0},{1},{2}]) = {3}")
    @CsvSource({
        "2, 4, 6, 4.0",
        "-3, -6, -9, -6.0",
        "-3, 0, 3, 0.0",
        "0, 0, 0, 0.0"
    })
    @DisplayName("average : classes d'équivalence pour trois éléments")
    void average_ThreeElements_ReturnsCorrectAverage(int a, int b, int c, double expected) {
        assertEquals(expected, ArrayUtils.average(new int[]{a, b, c}), 0.0001);
    }

    @Test
    @DisplayName("average : sampleArray {3,1,4,1,5} => 2.8")
    void average_SampleArray_ReturnsCorrectAverage() {
        assertEquals(2.8, ArrayUtils.average(sampleArray), 0.0001);
    }

    // ─── 3. Tests pour countOccurrences(int[], int) ───────────────────────────

    @Test
    @DisplayName("countOccurrences : null => IllegalArgumentException")
    void countOccurrences_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> ArrayUtils.countOccurrences(null, 5));
    }

    @Test
    @DisplayName("countOccurrences : élément absent")
    void countOccurrences_ElementAbsent_ReturnsZero() {
        assertEquals(0,
            ArrayUtils.countOccurrences(new int[]{1, 2, 3}, 9));
    }

    @Test
    @DisplayName("countOccurrences : élément au milieu")
    void countOccurrences_ElementInMiddle_ReturnsCorrectCount() {
        assertEquals(2,
            ArrayUtils.countOccurrences(new int[]{1, 7, 7, 2}, 7));
    }

    @ParameterizedTest(name = "countOccurrences([1,2,3,3,3], {0}) = {1}")
    @CsvSource({
        "1, 1",
        "2, 1",
        "3, 3",
        "9, 0"
    })
    @DisplayName("countOccurrences : valeurs différentes dans tableau fixe")
    void countOccurrences_FixedArray_ReturnsCorrectCount(int target, int expected) {
        assertEquals(expected,
            ArrayUtils.countOccurrences(new int[]{1, 2, 3, 3, 3}, target));
    }

    @Test
    @DisplayName("countOccurrences : BUG — élément à l'index 0 non compté")
    void countOccurrences_ElementAtIndex0_ShouldRevealBug() {
        // BUG : le cycle commence à i=1, donc l'index 0 est ignoré
        assertEquals(2,
            ArrayUtils.countOccurrences(new int[]{5, 1, 2, 5}, 5));
    }

    @Test
    @DisplayName("countOccurrences : BUG — seul élément à l'index 0")
    void countOccurrences_OnlyIndex0_ShouldRevealBug() {
        assertEquals(1,
            ArrayUtils.countOccurrences(new int[]{42, 1, 2, 3}, 42));
    }

    // ─── 4. Tests pour reverse(int[]) ────────────────────────────────────────

    @Test
    @DisplayName("reverse : null => IllegalArgumentException")
    void reverse_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> ArrayUtils.reverse(null));
    }

    @Test
    @DisplayName("reverse : tableau vide")
    void reverse_EmptyArray_ReturnsEmptyArray() {
        assertArrayEquals(new int[]{},
            ArrayUtils.reverse(new int[]{}));
    }

    @Test
    @DisplayName("reverse : un seul élément")
    void reverse_SingleElement_ReturnsSameArray() {
        assertArrayEquals(new int[]{7},
            ArrayUtils.reverse(new int[]{7}));
    }

    @Test
    @DisplayName("reverse : BUG — deux éléments non inversés")
    void reverse_TwoElements_ShouldRevealBug() {
        assertArrayEquals(new int[]{2, 1},
            ArrayUtils.reverse(new int[]{1, 2}));
    }

    @Test
    @DisplayName("reverse : BUG — trois éléments non inversés")
    void reverse_ThreeElements_ShouldRevealBug() {
        assertArrayEquals(new int[]{3, 2, 1},
            ArrayUtils.reverse(new int[]{1, 2, 3}));
    }

    @ParameterizedTest(name = "reverse d'une valeur unique {0}")
    @ValueSource(ints = {0, -1, 100, Integer.MAX_VALUE, Integer.MIN_VALUE})
    @DisplayName("reverse : valeurs limites uniques")
    void reverse_SingleBoundaryValues_ReturnSameValue(int value) {
        assertArrayEquals(new int[]{value},
            ArrayUtils.reverse(new int[]{value}));
    }
}