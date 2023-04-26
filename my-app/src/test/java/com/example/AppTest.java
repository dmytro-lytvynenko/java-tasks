package com.example;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private static String testFilePath;

    @BeforeAll
    static void setupAll() {
        testFilePath = "/home/tango/univ/msv/my-app/testtext.txt";
    }

    @BeforeEach
    void setup() {
        // можливо щось потрібно буде зробити перед кожним тестом
    }

    @Test
    @DisplayName("Test for counting vowels")
    void testCountingVowels() throws IOException {
        String testContents = "Aerial idea. Strength length.";
        LinkedHashSet<String> expected = new LinkedHashSet<>();
        expected.add("Aerial");
        expected.add("idea");

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Test for empty input string")
    void testEmptyInput() throws IOException {
        String testContents = "";
        LinkedHashSet<String> expected = new LinkedHashSet<>();

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Test for file read")
    void testFileRead() throws IOException {
        String expected = "This is a test file.\nIt contains some words with a lot of vowels.\n";

        String actual = App.readUsingScanner(testFilePath);
        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Test for words with more consonants or equal number of vowels and consonants")
    void testWordsWithMoreConsonantsOrEqual() throws IOException {
        String testContents = "Strength tree";
        LinkedHashSet<String> expected = new LinkedHashSet<>();

        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(testContents);
        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Test for file not found")
    void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            App.readUsingScanner("nonexistent_file.txt");
        });
    }

    @Test
    @DisplayName("Test for exception in counting vowels")
    void testCountingVowelsException() {
        assertThrows(NullPointerException.class, () -> {
            App.getWordsWithMoreVowels(null);
        });
    }

    @Test
    @DisplayName("Test for using Hamcrest matchers")
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

    @ParameterizedTest
    @DisplayName("Test for parameterized input")
    @CsvSource({
            "Aeonian eon of an input string, Aeonian, true",
            "Aeonian, Aeonian, true",
            "string, Aeonian, false"
        })
    void testParameterizedInput(String inputString, String expectedWord, boolean shouldContainWord) throws IOException {
        LinkedHashSet<String> actual = App.getWordsWithMoreVowels(inputString);
        if (shouldContainWord) {
            assertThat(actual, hasItem(expectedWord));
        } else {
            assertThat(actual, not(hasItem(expectedWord)));
        }
    }
            
        
}
