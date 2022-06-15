package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.List;

public class ControlQuality {

    private List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    public void redefine(Food food) {
        for (Store elem : store) {
            elem.add(food);
        }
    }

}
