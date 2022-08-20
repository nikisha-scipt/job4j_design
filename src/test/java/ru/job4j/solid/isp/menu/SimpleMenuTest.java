package ru.job4j.solid.isp.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        assertEquals(menu.select("Купить продукты").get(),
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб"), STUB_ACTION, "1.1."));
    }

    @Test
    public void whenPrintMenu() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(byteArrayOutputStream));
            Menu menu = new SimpleMenu();
            menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
            menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
            menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
            menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
            MenuPrinter consoleMenuPrinter = new ConsoleMenuPrinter();
            consoleMenuPrinter.print(menu);
            String builder = "1.Сходить в магазин"
                   +  System.lineSeparator()
                   +  "----1.1.Купить продукты"
                   +  System.lineSeparator()
                   +  "--------1.1.1.Купить хлеб"
                   +  System.lineSeparator()
                   +  "2.Покормить собаку"
                   +  System.lineSeparator();
            assertEquals(builder, byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}