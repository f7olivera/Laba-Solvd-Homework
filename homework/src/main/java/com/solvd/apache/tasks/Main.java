package com.solvd.apache.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordCounter.countWords("src/main/resources/article.txt", "src/main/resources/article-count.txt");

        // SecondTask.createRandomFiles() example
        // FilesManager.createRandomFiles(directoryPath, 5);

        // SecondTask.getLastModified() example
        String directoryPath = "src/main/resources/random/";

        // Get last modified file
        System.out.print("Last modified file in " + directoryPath + ": ");
        System.out.println(SecondTask.getLastModified(directoryPath).getName());

        // SecondTask.renameFile() example
        try {
            SecondTask.renameFile("src/main/resources/random/original.txt", "new.txt");
        } catch (FileNotFoundException e) {
            SecondTask.renameFile("src/main/resources/random/new.txt", "original.txt");
        }

        // SecondTask.rotateWord() example
        String wordToRotate = "text";
        System.out.printf("Rotating word \"%s\": ", wordToRotate);
        System.out.println(SecondTask.rotateWord("This is some random text (text).", wordToRotate, 1));

        // SecondTask.containsAny() example
        System.out.println(SecondTask.containsAny("Lorem ipsum dolor sit amet.", new String[]{"false"}));
        System.out.println(SecondTask.containsAny("Lorem ipsum dolor sit amet.", new String[]{"sit"}));
    }
}
