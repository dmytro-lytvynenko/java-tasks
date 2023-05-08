package com.example;

import org.testng.annotations.*;
import org.hamcrest.Matchers;
import java.io.IOException;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class AppTest {

    private static String testFilePath;

    @BeforeClass
    public static void setupAll() {
        testFilePath = "/home/tango/univ/msv/my-app/testtext.txt";
    }

    @BeforeMethod
    public void setup() {
        // можливо щось потрібно буде зробити перед кожним тестом
    }

    @Test(groups = {"vowels"})
    void testCountingVowels() throws IOException {
        String testContents = "Aerial idea. Strength length.";
        LinkedHashSet<String> expected = new LinkedHashSet<>();
        expected.add("Aerial");
        expected.add("idea");

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test(groups = {"vowels"})
    void testEmptyInput() throws IOException {
        String testContents = "";
        LinkedHashSet<String> expected = new LinkedHashSet<>();

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test(groups = {"vowels"})
    void testFileRead() throws IOException {
        String expected = "This is a test file.\nIt contains some words with a lot of vowels.\n";

        String actual = App.readUsingScanner(testFilePath);
        assertThat(actual, is(expected));
    }

    @Test(groups = {"vowels"})
    void testWordsWithMoreConsonantsOrEqual() throws IOException {
        String testContents = "Strength tree";
        LinkedHashSet<String> expected = new LinkedHashSet<>();

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test(groups = {"exceptions"})
    void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            App.readUsingScanner("nonexistent_file.txt");
        });
    }

    @Test(groups = {"exceptions"})
    void testCountingVowelsException() {
        assertThrows(NullPointerException.class, () -> {
            App.getWordsWithMoreVowels(null);
        });
    }

    @Test(groups = {"vowels"})
    void testHamcrestMatchers() throws IOException {
        String testContents = "aeri adea. strength length.";
        LinkedHashSet<String> expected = new LinkedHashSet<>();
        expected.add("adea");
        expected.add("aeri");

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, containsInAnyOrder("adea", "aeri"));
        assertThat(actual, hasSize(2));
        assertThat(actual, everyItem(not(isEmptyOrNullString())));
        assertThat(actual, everyItem(Matchers.startsWith("a")));
    }

    @DataProvider
    public Object[][] inputDataProvider() {
        return new Object[][] {
            {"Aeonian eon of an input string", "Aeonian", true},
            {"Aeonian", "Aeonian", true},
            {"string", "Aeonian", false}
        };
    }

    @Test(dataProvider = "inputDataProvider", groups = {"parameterized"})
    public void testParameterizedInput(String inputString, String expectedWord, boolean shouldContainWord) throws IOException {
        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(inputString);
        if (shouldContainWord) {
            assertThat(actual, hasItem(expectedWord));
        } else {
            assertThat(actual, not(hasItem(expectedWord)));
        }
    }
}
