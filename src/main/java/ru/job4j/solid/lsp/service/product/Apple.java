package ru.job4j.solid.lsp.service.product;

import java.time.LocalDateTime;

public class Apple extends Food {
    public Apple(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
