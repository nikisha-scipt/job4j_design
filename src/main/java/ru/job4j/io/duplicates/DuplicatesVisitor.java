package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    List<FileProperty> filesDupl = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName(), file.toAbsolutePath());
        filesDupl.add(fileProperty);
        return super.visitFile(file, attrs);
    }

    public void printFiles() {
        for (int i = 0; i < filesDupl.size(); i++) {
            for (int j = 0; j < filesDupl.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (filesDupl.get(i).equals(filesDupl.get(j))) {
                    System.out.printf("Path - %s name file - %s%n", filesDupl.get(i).getPath(), filesDupl.get(i).getName());
                    break;
                }
            }
        }

    }

}
