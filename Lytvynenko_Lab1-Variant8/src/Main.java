import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import java.util.LinkedHashSet;

public class Main
{
    public static void main(String[] args) throws IOException {
        String filepath = "\\Users\\Home\\source\\repos\\text.txt";
        String contents = readUsingScanner(filepath);
        System.out.println("\n\nContents:\n\n" + contents);
        contents = contents.replace("\"", "").replace("-", "").replace(",", "").replace(".", "");

        String[] words = contents.split(" ");
        char[] vowels = {'A', 'a', 'E', 'e','I', 'i', 'O', 'o','U' ,'u', 'Y', 'y'};
        LinkedHashSet<String> result_words = new LinkedHashSet<>();

        for(String word : words) {
            long vowels_counter = 0;
            for(char vowel : vowels) {
                vowels_counter += word.chars().filter(ch -> ch == vowel).count();
            }

            if(vowels_counter > word.length() / 2) {
                result_words.add(word);
            }
        }

        System.out.println("\n\n\nWords where are more vowels than consonants:\n");
        if( result_words.isEmpty() ) {
            System.out.println("no such words");
        }
        else {
            for (String word : result_words) {
                System.out.println(word);
            }
        }
    }
    private static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }
}
