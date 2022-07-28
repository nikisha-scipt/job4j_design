package ru.job4j.io.finder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validation {

    private ArgsFilesDelimiter args;
    private Path root;
    private String nameFile;
    private String outputFile;
    private String typeFinder;
    private final int size;

    public Validation(String[] args) {
        this.args = ArgsFilesDelimiter.of(args);
        this.size = args.length;
    }

    private boolean checkSize() {
        return size == 4;
    }

    private boolean checkDir() {
        boolean res = false;
        File temp = new File(args.get("d"));
        if (temp.isDirectory()) {
            root = Paths.get(temp.getPath());
            res = true;
        }
        return res;
    }

    private boolean checkFileMask() {
        boolean res = false;
        if (args.get("n").equals("*.?")) {
            nameFile = args.get("n");
            res = true;
        }
        return res;
    }

    private boolean checkFileName() {
        boolean res = false;
        if (!args.get("n").isEmpty()) {
            nameFile = args.get("n");
            res = true;
        }
        return res;
    }


    private boolean checkTypeFinder() {
        boolean res = false;
        typeFinder = args.get("t");
        if (typeFinder.equals("mask")) {
            res = checkFileMask();
        } else if (typeFinder.equals("name")) {
            res = checkFileName();
        }
        return res;
    }

    private boolean out() {
        boolean res = false;
        if (args.get("o").contains(".")) {
            outputFile = args.get("o");
            res = true;
        }
        return res;
    }

    public boolean isValid() {
        try {
            checkSize();
            checkDir();
            checkTypeFinder();
            out();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Format:java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt");
        }
        return true;
    }

    public Path getRoot() {
        return root;
    }

    public String getNameFile() {
        return nameFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getTypeFinder() {
        return typeFinder;
    }
}
