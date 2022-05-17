package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> maps = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        maps.putIfAbsent(fileProperty, new ArrayList<>());
        maps.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printFiles() {
        for (List<Path> entry : maps.values()) {
            if (entry.size() >= 2) {
                System.out.println(entry);
            }
        }
    }

}
