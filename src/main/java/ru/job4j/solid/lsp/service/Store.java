package ru.job4j.solid.lsp.service;

import ru.job4j.solid.lsp.service.product.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {

    boolean add(Food food);

    List<Food> findAllFood();

    default double bestBeforeDate(Food food) {
        double life = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double remain = ChronoUnit.DAYS.between(LocalDate
                .now(), food.getExpiryDate());
        return remain / life * 100;
    }

}
