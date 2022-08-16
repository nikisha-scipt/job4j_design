package ru.job4j.gcdemo.leak;

import ru.job4j.gcdemo.leak.model.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "D:\\java\\job4j\\project\\job4j_design\\src\\main\\java\\ru\\job4j\\gcdemo\\leak\\utils\\phrases.txt";
    public static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public static final int COUNT = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct().limit(3).forEach(ints::add);
        for (int i = 0; i < COUNT; i++) {
            String comment = phrases.get(ints.get(0)) + SEPARATOR
                    + phrases.get(ints.get(1)) + SEPARATOR
                    + phrases.get(ints.get(2));
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }

}
