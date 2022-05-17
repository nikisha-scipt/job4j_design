package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        packSingleFile(sources, target);
    }

    public void packSingleFile(List<Path> sources, Path target) {
        for (Path file : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(file)))) {
                    zip.write(out.readAllBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void valid(ArgsName args) throws IllegalAccessException {
        if (!args.get("d").equals("directory")) {
            throw new IllegalAccessException("Invalid directory");
        }
        if (!args.get("e").equals("exclude")) {
            throw new IllegalAccessException("Invalid exclude");
        }
        if (!args.get("o").equals("output")) {
            throw new IllegalAccessException("Invalid output");
        }
    }


    public static void main(String[] args) throws IOException, IllegalAccessException {
        if (args.length != 3) {
            throw new IllegalArgumentException("format: -d=c:\\project -e=.class -o=project.zip");
        }
        ArgsName argsName = ArgsName.of(args);
        valid(argsName);
        List<Path> files = Search.search(Path.of(argsName.get("d")), element -> !element.toFile().getName().endsWith(argsName.get("e")));
        new Zip().packFiles(files, Path.of(argsName.get("o")));

        /* Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        ); */
    }

}
