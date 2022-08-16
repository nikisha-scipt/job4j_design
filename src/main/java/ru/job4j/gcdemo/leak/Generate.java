package ru.job4j.gcdemo.leak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Generate {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String read = reader.readLine(); read != null; read = reader.readLine()) {
                text.add(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
