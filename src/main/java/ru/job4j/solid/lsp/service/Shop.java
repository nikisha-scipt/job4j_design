package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (accept(food)) {
            if (bestBeforeDate(food) > 0 && bestBeforeDate(food) < 25) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            foodList.add(food);
            res =  true;
        }
        return res;
    }

    @Override
    public boolean accept(Food food) {
        return bestBeforeDate(food) > 0 && bestBeforeDate(food) <= 75;
    }

    @Override
    public List<Food> findAllFood() {
        List<Food> list = new ArrayList<>(foodList);
        foodList.clear();
        return list;
    }
}
