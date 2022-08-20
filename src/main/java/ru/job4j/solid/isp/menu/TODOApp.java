package ru.job4j.solid.isp.menu;

import java.io.*;

public class TODOApp {

    public static final int ADD_TASK = 1;
    public static final int SHOW_ALL_TASK = 2;

    public static final ActionDelegate ACTION = System.out::println;

    public static final String SELECT = "Выберите меню";
    public static final String TASK = "Ввести задачу?";
    public static final String SELECT_TASK = "Введите задачу: ";
    public static final String SUBTASK = "Введите подзадачу: ";

    public static final String YES = "yes";

    public static final StringBuilder MENU = new StringBuilder("Введите 1 для добавление задачи в корень.\n"
            + "Введите 2, для вывода меню.\n"
            + "Введите любое другое число для выхода.");

    public void start() throws IOException {
        Menu menuSimple = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        int select;
        String answer;
        boolean flag = true;
        while (flag) {
            System.out.println(MENU);
            System.out.println(SELECT);
            select = Integer.parseInt(getString());
            switch (select) {
                case ADD_TASK:
                    System.out.println(TASK);
                    answer = getString();
                    if (YES.equals(answer)) {
                        System.out.println(SELECT_TASK);
                        answer = getString();
                        menuSimple.add(Menu.ROOT, answer, ACTION);
                    } else {
                        System.out.println(SELECT_TASK);
                        answer = getString();
                        System.out.println(SUBTASK);
                        String subTaskAnswer = getString();
                        menuSimple.add(answer, subTaskAnswer, ACTION);
                    }
                    break;
                case SHOW_ALL_TASK:
                    printer.print(menuSimple);
                    break;
                default: flag = false;
            }
        }
    }

    private String getString() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static void main(String[] args) throws IOException {
        TODOApp todo = new TODOApp();
        todo.start();
    }

}
