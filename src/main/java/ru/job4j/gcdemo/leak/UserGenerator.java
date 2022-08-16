package ru.job4j.gcdemo.leak;

import ru.job4j.gcdemo.leak.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\names.txt";
    public static final String PATH_SURNAMES = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\surnames.txt";
    public static final String PATH_PATRONS = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\patr.txt";

    public static final String SEPARATOR = " ";
    public static final int NEW_USERS = 1000;

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
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void generate() {
        users.clear();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < NEW_USERS; i++) {
            builder.append(surnames.get(random.nextInt(surnames.size())))
                    .append(SEPARATOR)
                    .append(names.get(random.nextInt(names.size())))
                    .append(SEPARATOR)
                    .append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(builder.toString()));
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

}
