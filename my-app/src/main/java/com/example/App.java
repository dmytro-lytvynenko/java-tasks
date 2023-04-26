package com.example;

import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        String filepath = "/home/tango/univ/msv/my-app/text.txt";
        String contents = readUsingScanner(filepath);
        System.out.println("\n\nContents:\n\n" + contents);
        Set<String> resultWords = getWordsWithMoreVowels(contents);
        System.out.println("\n\n\nWords where are more vowels than consonants:\n");
        if (resultWords.isEmpty()) {
            System.out.println("no such words");
        } else {
            for (String word : resultWords) {
                System.out.println(word);
            }
        }
    }

    public static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }

    public static LinkedHashSet<String> getWordsWithMoreVowels(String contents) {
        contents = contents.replace("\"", "").replace("-", "").replace(",", "").replace(".", "");
        String[] words = contents.split(" ");
        char[] vowels = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u', 'Y', 'y'};
        Set<String> resultWords = new LinkedHashSet<>();
        for (String word : words) {
            long vowelsCounter = 0;
            for (char vowel : vowels) {
                vowelsCounter += word.chars().filter(ch -> ch == vowel).count();
            }
            if (vowelsCounter > word.length() / 2) {
                resultWords.add(word);
            }
        }
        return new LinkedHashSet<>(resultWords);
    }
}