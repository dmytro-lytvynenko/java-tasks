package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public interface FileService {
    String readFile(String fileName) throws IOException;
}