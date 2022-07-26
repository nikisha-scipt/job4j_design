package ru.job4j.io.finder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Validation val = new Validation(args);
        if (val.isValid()) {
            FinderFiles files = new FinderFiles(val.getRoot(), val.getTypeFinder(), val.getNameFile());
            files.of();
            List<Path> out = files.getFiles();
            try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(val.getOutputFile())))) {
                out.forEach(writer::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
