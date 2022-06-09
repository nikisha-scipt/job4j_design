package ru.job4j.gcdemo.ref.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Укажите кэшируемую директорию: ");
        String path = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(path);
        System.out.print("Укажите название кэшируемого файла: ");
        String file = scanner.nextLine();
        System.out.print("Загрузить и получить содержимое файла в кэш? ");
        String answer = scanner.nextLine();
        if ("yes".equals(answer)) {
            dirFileCache.put(file, dirFileCache.load(file));
            System.out.println(dirFileCache.get(file));
        } else {
           System.out.println("wrong answer");
        }
    }

}
