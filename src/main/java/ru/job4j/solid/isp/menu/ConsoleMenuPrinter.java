package ru.job4j.solid.isp.menu;


public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int count = menuItemInfo.getNumber().split("\\.").length - 1;
            System.out.println("----".repeat(count)
                    + menuItemInfo.getNumber()
                    + menuItemInfo.getName());
        }
    }
}
