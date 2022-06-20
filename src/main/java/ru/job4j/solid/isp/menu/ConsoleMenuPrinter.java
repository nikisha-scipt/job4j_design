package ru.job4j.solid.isp.menu;


public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            System.out.println(menuItemInfo);
        }
    }
}
