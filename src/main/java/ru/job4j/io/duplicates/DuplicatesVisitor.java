package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    List<FileProperty> filesDupl = new ArrayList<>();
    List<Path> paths = new ArrayList<>();
    Map<Path, FileProperty> maps = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
       filesDupl.add(fileProperty);
        paths.add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printFiles() {
        for (int i = 0; i < filesDupl.size(); i++) {
            for (int j = 0; j < filesDupl.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (filesDupl.get(i).equals(filesDupl.get(j))) {
                   maps.put(paths.get(i), filesDupl.get(i));
                    break;
                }
            }
        }
        maps.entrySet().forEach(System.out::println);

    }

}
