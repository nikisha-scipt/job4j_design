package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class Valid {

        private final ArgsName args;
        private final int size;

        public Valid(String[] args) {
            this.args = ArgsName.of(args);
            this.size = args.length;
        }

        private boolean checkSize() {
            return size == 3;
        }

        public String dir() {
            return args.get("d");
        }

        public String exclude() {
            return args.get("e");
        }

        public String out() {
            return args.get("o");
        }

        public boolean isValid() {
            try {
                checkSize();
                dir();
                exclude();
                out();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return true;
        }

    }


    public static void main(String[] args) throws IOException, IllegalAccessException {
        Valid valid = new Valid(args);
        if (valid.isValid()) {
            Path root = Paths.get(valid.dir());
            List<Path> sourcesPath = Search.search(root, p ->
                    !p.toFile().getName().endsWith(valid.exclude()));
            List<File> sources = sourcesPath.stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            new Zip().packFiles(sources, new File(valid.out()));
        }
    }

}
