package ru.job4j.solid.lsp.service.product;

import java.time.LocalDateTime;

public class Milk extends Food {
    public Milk(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
