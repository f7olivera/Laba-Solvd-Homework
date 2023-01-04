package com.solvd.apache.tasks;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static java.nio.charset.Charset.defaultCharset;

public final class WordCounter {
    public static void countWords(String inputPath, String outputPath) throws IOException {
        HashMap<String, Integer> occurrences = new HashMap<>();

        for (String word : StringUtils.lowerCase(FileUtils.readFileToString(new File(inputPath), defaultCharset())).split("\\W+"))
            occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);

        FileUtils.write(new File(outputPath), occurrences.toString().replace(", ", ",\n"), defaultCharset());
    }
}
