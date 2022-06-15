package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.util.List;

public class Warehouse implements Store {

    List<Food> foodList;

    public Warehouse(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (bestBeforeDate(food) > 75) {
            foodList.add(food);
            res = true;
        }
        return res;
    }

    @Override
    public List<Food> findAllFood() {
        return foodList;
    }
}
