package ru.job4j.gcdemo.leak;

import ru.job4j.gcdemo.leak.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public final String pathNames = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\names.txt";
    public final String pathSurnames = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\surnames.txt";
    public final String pathPatrons = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\patr.txt";

    public final String separator = " ";
    public final Integer newUsers = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;

    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    private void readAll() {
        try {
            names = read(pathNames);
            surnames = read(pathSurnames);
            patrons = read(pathPatrons);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < newUsers; i++) {
            users.add(new User(
                    surnames.get(random.nextInt(surnames.size())) + separator
                            + names.get(random.nextInt(names.size())) + separator
                            + patrons.get(random.nextInt(patrons.size()))));
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
