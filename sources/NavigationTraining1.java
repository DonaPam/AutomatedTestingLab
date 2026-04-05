package ru.spbstu.hsai;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NavigationTraining1 {

    @Test
    public void testSupportPageFullCheck() {
        SoftAssert softAssert = new SoftAssert();

        // 1. Navigate to the "Support" page using the main menu navigation

        // 2. Assert that the page title contains the word "support" (case-insensitive)

        // 3. Assert that a form or contact block is present on the page

        // 4. Assert that the form is displayed and visible to the user

        // 5. Assert that the form contains at least one input field (<input>)

        // 6. Assert that the form contains at least one textarea element (<textarea>)

        // 7. Assert that the form contains a submit button (<button> or <input type="submit">)

        softAssert.assertAll();
    }

}
