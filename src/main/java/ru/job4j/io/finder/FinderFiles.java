package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FinderFiles extends SimpleFileVisitor<Path> {

    private List<Path> files;
    private Path path;
    private String type;
    private String fileFinder;

    public FinderFiles(Path path, String type, String fileFinder) {
        this.files = new ArrayList<>();
        this.path = path;
        this.type = type;
        this.fileFinder = fileFinder;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (type.equals("mask") && file.toFile().getName().matches(fileFinder
                .replace(".", "[.]")
                .replace("*", ".+")
                .replace("?", "."))) {
            files.add(file);
        } else if (type.equals("name") && file.toFile().getName().equals(fileFinder)) {
            files.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public void of() throws IOException {
        Files.walkFileTree(path, this);
    }

    public List<Path> getFiles() {
        return files;
    }
}
