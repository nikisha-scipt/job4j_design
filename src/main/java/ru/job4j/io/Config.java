package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (line.contains("=")) {
                    String[] temp = line.split("=");
                    addValue(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addValue(String[] strings) {
        if (strings.length == 3) {
            String tempValue = strings[1] + " = " + strings[2];
            values.put(strings[0], tempValue);
        } else if (strings.length == 2) {
            values.put(strings[0], strings[1]);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.password"));
    }
}
