package com.solvd.apache.tasks;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordCounter.countWords3("src/main/resources/article.txt", "src/main/resources/article-count.txt");

        String directoryPath = "src/main/resources/random/";

        // FilesManager.createRandomFiles(directoryPath, 5);

        // Get last modified file
        System.out.print("Last modified file in " + directoryPath + ":");
        System.out.println(SecondTask.getLastModified(directoryPath).getName());

        // SecondTask.renameFile("src/main/resources/random/original.txt", "new.txt");

        System.out.println(SecondTask.rotateWord("This is some random text (text).", "text", 1));
    }
}
