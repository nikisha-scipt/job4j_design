package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        throw new IllegalArgumentException("no value for this key ");
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] temp = arg.split("=", 2);
            valid(temp);
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    private void valid(String[] arr) {
        if (arr.length < 2 || arr[0].isEmpty() || arr[1].isEmpty()) {
            throw new IllegalArgumentException("format: 'key=value'");
        }
        if (!arr[0].startsWith("-") || arr[0].length() < 2) {
            throw new IllegalArgumentException("format: -key=value when key length more or equals 2");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("you passed empty values");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
