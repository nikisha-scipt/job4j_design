package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (accept(food)) {
            foodList.add(food);
            res = true;
        }
        return res;
    }

    @Override
    public boolean accept(Food food) {
        return bestBeforeDate(food) > 75;
    }


    @Override
    public List<Food> findAllFood() {
        List<Food> list = new ArrayList<>(foodList);
        foodList.clear();
        return list;
    }
}
