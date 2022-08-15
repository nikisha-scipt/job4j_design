package ru.job4j.gcdemo.leak;

import ru.job4j.gcdemo.leak.model.Post;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    public static final Integer ADD_POST = 1;
    public static final Integer ADD_MANY_POST = 2;
    public static final Integer SHOW_ALL_POSTS = 3;
    public static final Integer DELETE_POST = 4;

    public final String select = "Выберите меню";
    public final String count = "Выберите количество создаваемых постов";
    public final String textOfPost = "Введите текст";
    public final String exit = "Конец работы";

    public final String menu =
                "Введите 1 для создание поста.\n"
                + "Введите 2, чтобы создать определенное количество постов.\n"
                + "Введите 3, чтобы показать все посты.\n"
                + "Введите 4, чтобы удалить все посты.\n"
                + "Введите любое другое число для выхода.";

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(exit);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    private void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }

    public static void main(String[] args) {
        Random random = new Random();
        Menu menu = new Menu();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

}
