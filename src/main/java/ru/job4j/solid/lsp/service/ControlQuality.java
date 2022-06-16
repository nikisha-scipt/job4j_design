package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    public void redefine(Food food) {
        for (Store elem : store) {
            if (elem.accept(food)) {
                elem.add(food);
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store elem : store) {
            foods.addAll(elem.findAllFood());
        }
        for (Food food : foods) {
            redefine(food);
        }
    }

}
