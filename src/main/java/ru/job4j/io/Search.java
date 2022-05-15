package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = valid(args);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static Path valid(String[] arr) {
        Path path;
        if (arr.length == 0 || arr.length > 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        path = Paths.get(arr[0]);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", path.toFile().getAbsoluteFile()));
        }
        if (!arr[1].startsWith(".")) {
            throw new IllegalArgumentException();
        }
        return path;
    }
}
