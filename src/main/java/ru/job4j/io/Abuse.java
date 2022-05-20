package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Abuse {

    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            reader.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word)).map(word -> word + " ")
                    .forEach(writer::print);
        }
    }

}
