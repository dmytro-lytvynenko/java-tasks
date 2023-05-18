package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class AppTest {
    @Mock
    FileService fileService;

    @Spy
    @InjectMocks
    App app;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        app = new App(fileService);
    }

    @Test
    public void testReadFile() throws IOException {
        String expectedContents = "Aerial aero abobing";

        // Configure the stub
        when(fileService.readFile(anyString())).thenReturn(expectedContents);

        LinkedHashSet<String> expectedHashSet = new LinkedHashSet<String>();
        expectedHashSet.add("Aerial");
        expectedHashSet.add("aero");

        String testFilePath = "home/tango/univ/msv/my-app/testtext.txt";
        LinkedHashSet<String> contents = app.getWordsWithMoreVowels(
            app.getFileService().readFile(testFilePath)
        );

        assertEquals(expectedHashSet, contents);

        // Verify that the method was called exactly once
        verify(fileService, times(1)).readFile(testFilePath);
    }

    @Test
    public void testReadFileException() throws IOException {
        when(fileService.readFile(anyString())).thenThrow(new IOException("File not found"));
        assertThrows(IOException.class, () -> {
            app.fileService.readFile("anyfilepath");
        });
        verify(fileService, times(1)).readFile("anyfilepath");
    }
}
