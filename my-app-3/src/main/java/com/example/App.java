package com.example;

import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

public class App {
    public FileService fileService;

    public App(FileService fileService) {
        this.fileService = fileService;
    }

    // Getter for fileService
    public FileService getFileService() {
        return this.fileService;
    }

    public LinkedHashSet<String> getWordsWithMoreVowels(String contents) {
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