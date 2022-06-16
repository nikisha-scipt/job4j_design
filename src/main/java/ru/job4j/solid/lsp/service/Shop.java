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
            food.setDiscount((int) food.getPrice());
            foodList.add(food);
            res =  true;
        }
        return res;
    }

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        if (bestBeforeDate(food) >= 25 && bestBeforeDate(food) <= 75) {
            res =  true;
        } else if (bestBeforeDate(food) > 0 && bestBeforeDate(food) < 25) {
            food.setDiscount((int) food.getPrice());
            res = true;
        }
        return res;
    }

    @Override
    public List<Food> findAllFood() {
        List<Food> list = new ArrayList<>(foodList);
        foodList.clear();
        return list;
    }
}
