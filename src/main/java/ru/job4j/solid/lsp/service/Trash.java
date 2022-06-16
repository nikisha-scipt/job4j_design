package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (accept(food)) {
            foodList.add(food);
            res =  true;
        }
        return res;
    }

    @Override
    public boolean accept(Food food) {
        return bestBeforeDate(food) <= 0;
    }

    @Override
    public List<Food> findAllFood() {
        return foodList.stream().toList();
    }
}
