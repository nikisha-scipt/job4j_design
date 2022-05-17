package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Integer> listNumberOfStr = new ArrayList<>();
        String[] strArr;
        valid(argsName);
        File file = new File(argsName.get("path"));
        try (Scanner scanner = new Scanner(file)) {
            try (Scanner scanTemp = new Scanner(file)) {
                strArr = scanTemp.nextLine().split(argsName.get("delimiter"));
                String[] args = argsName.get("filter").split(",");
                for (String arg : args) {
                    for (int i = 0; i < strArr.length; i++) {
                        if (arg.equals(strArr[i])) {
                            listNumberOfStr.add(i);
                        }
                    }
                }
            }
            while (scanner.hasNext()) {
                strArr = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < strArr.length; i++) {
                    for (int j = 0; j < listNumberOfStr.size(); j++) {
                        if (i == listNumberOfStr.get(j)) {
                            if (j == listNumberOfStr.size() - 1) {
                                sb.append(strArr[i]).append(System.lineSeparator());
                            } else {
                                sb.append(strArr[i]).append(";");

                            }
                        }
                    }
                }
            }
            try (BufferedWriter bf = new BufferedWriter(new FileWriter(argsName.get("out")))) {
                bf.write(sb.toString());
            }
            System.out.println(sb);
        }

    }

    private static void valid(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("No such this file");
        }
        if (argsName.get("delimiter").equals(";" + "\n") || argsName.get("delimiter").equals("," + "\n")) {
            throw new IllegalArgumentException("Invalid delimiter");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

}
