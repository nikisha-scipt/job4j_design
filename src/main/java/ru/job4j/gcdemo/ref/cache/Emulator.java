package ru.job4j.gcdemo.ref.cache;

import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Укажите кэшируемую директорию: ");
        String path = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(path);
        System.out.print("Укажите название кэшируемого файла: ");
        String file = scanner.nextLine();
        dirFileCache.put(file, dirFileCache.load(path + file));
        System.out.println(dirFileCache.get(file));
    }

}
