package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        for (Path file : sources) {
            packSingleFile(file, target);
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(String.valueOf(source))
            )) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String findValue(List<?> arrList, String value) {
        String res = null;
        for (Object o : arrList) {
            if (value.equals(o)) {
                res = (String) o;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Enter: -d - directory - which we want to archive. -e - exclude - exclude files with class extension. -o - output - what we archive into.");
        }
        ArgsName argsName = ArgsName.of(args);
        List<String> arrKeys = argsName.get();
        for (String arrKey : arrKeys) {
            if (!arrKey.equals("-d") && !arrKey.equals("-e") && !arrKey.equals("-o")) {
                throw new IllegalArgumentException("-d directory, -e exclude, -o output");
            }
        }
        List<Path> files = Search.search(Path.of(findValue(arrKeys, "-d")), element -> !element.toFile().getName().endsWith(findValue(arrKeys, "-e")));
        new Zip().packFiles(files, Path.of(findValue(arrKeys, "-o")));

        /* Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        ); */
    }

}
