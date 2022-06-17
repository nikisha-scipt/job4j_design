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
        private String root;
        private String exclude;
        private String out;
        private final int size;

        public Valid(String[] args) {
            this.args = ArgsName.of(args);
            this.size = args.length;
        }

        private boolean checkSize() {
            return size == 3;
        }

        public boolean dir() {
            boolean res = false;
            File temp = new File(args.get("d"));
            if (temp.isDirectory()) {
                root = String.valueOf(Paths.get(temp.getPath()));
                res = true;
            }
            return res;
        }

        public boolean exclude() {
            boolean res = false;
            if (args.get("e").startsWith(".")) {
                exclude = args.get("e");
                res = true;
            }
            return res;
        }

        public boolean out() {
            boolean res = false;
            if (args.get("o").contains(".zip")) {
                out = args.get("o");
                res = true;
            }
            return res;
        }

        public boolean isValid() {
            try {
                checkSize();
                dir();
                exclude();
                out();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Format:-d=C:\\ -e=.class -o=project.zip");
            }
            return true;
        }

        public String getRoot() {
            return root;
        }

        public String getExclude() {
            return exclude;
        }

        public String getOut() {
            return out;
        }

    }


    public static void main(String[] args) throws IOException, IllegalAccessException {
        Valid valid = new Valid(args);
        if (valid.isValid()) {
            Path root = Paths.get(valid.getRoot());
            List<Path> sourcesPath = Search.search(root, p ->
                    !p.toFile().getName().startsWith(valid.getExclude()));
            List<File> sources = sourcesPath.stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            new Zip().packFiles(sources, new File(valid.getOut()));
        }

    }

}
