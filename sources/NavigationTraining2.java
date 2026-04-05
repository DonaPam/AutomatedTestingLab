package ru.spbstu.hsai;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NavigationTraining2 {

    @Test
    public void testDifferentElementsPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();

        // 1. Navigate to the "Different elements" page using the main menu navigation

        // 2. Assert that the page title contains both "different" and "element" (case-insensitive)

        // 3. Assert that at least 4 buttons (input[type='button'] or <button>) are present on the page

        // 4. Assert that at least 4 checkboxes (input[type='checkbox']) are present on the page

        // 5. Assert that at least 4 radio buttons (input[type='radio']) are present on the page

        // 6. Assert that at least one dropdown (<select> element) exists on the page

        // 7. If dropdown exists: assert it is displayed and contains at least 2 options

        // 8. Assert that the dropdown options include both "Red" and "Green" values

        // 9. Assert that checkbox labels with class "label-checkbox" are present (at least 2)

        softAssert.assertAll();
    }

}
