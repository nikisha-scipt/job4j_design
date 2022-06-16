package ru.job4j.solid.lsp.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.solid.lsp.service.product.Apple;
import ru.job4j.solid.lsp.service.product.Food;
import ru.job4j.solid.lsp.service.product.Milk;
import ru.job4j.solid.lsp.service.product.Orange;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;


public class ControlQualityTest {

    private List<Store> storeList;
    private ControlQuality controlQuality;
    private Store shop;
    private Store warehouse;
    private Store trash;

    @Before
    public void init() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        storeList = List.of(
                shop,
                warehouse,
                trash
        );
        controlQuality = new ControlQuality(storeList);
    }

    @Test
    public void whenAddToShop() {
        Food milk = new Milk("Milk", LocalDateTime.now().plusMonths(3), LocalDateTime.now().minusMonths(8), 77, 8);
        controlQuality.redefine(milk);
        shop = storeList.get(0);
        assertThat(shop.findAllFood(), is(List.of(milk)));
    }


    @Test
    public void whenAddToTrash() {
        Food orange = new Orange("Orange", LocalDateTime.now(), LocalDateTime.now().minusMonths(1), 7, 10);
        controlQuality.redefine(orange);
        shop = storeList.get(2);
        assertThat(shop.findAllFood(), is(List.of(orange)));
    }

    @Test
    public void whenAddToWarehouse() {
        Food apple = new Apple("Apple", LocalDateTime.now().plusMonths(8), LocalDateTime.now().minusMonths(2), 177, 11);
        controlQuality.redefine(apple);
        shop = storeList.get(1);
        assertThat(shop.findAllFood(), is(List.of(apple)));
    }

    @Test
    public void whenAddToShopAndApplyDiscount() {
        Food apple = new Apple("Apple", LocalDateTime.now().plusDays(3), LocalDateTime.now().minusMonths(1), 11, 29);
        controlQuality.redefine(apple);
        shop = storeList.get(0);
        assertThat(shop.findAllFood(), is(List.of(apple)));
    }

    @Test
    public void whenAddMoreFood() {
        Food milk = new Milk("Milk", LocalDateTime.now().plusMonths(3), LocalDateTime.now().minusMonths(8), 77, 8);
        Food apple = new Apple("Apple", LocalDateTime.now().plusMonths(8), LocalDateTime.now().minusMonths(2), 177, 11);
        Food orange = new Orange("Orange", LocalDateTime.now(), LocalDateTime.now().minusMonths(1), 7, 10);
        controlQuality.redefine(milk);
        controlQuality.redefine(apple);
        controlQuality.redefine(orange);
        shop = storeList.get(0);
        warehouse = storeList.get(1);
        trash = storeList.get(2);
        assertThat(shop.findAllFood(), is(List.of(milk)));
        assertThat(warehouse.findAllFood(), is(List.of(apple)));
        assertThat(trash.findAllFood(), is(List.of(orange)));
    }

    @Test(expected = AssertionError.class)
    public void whenAddTrashFoodInWarehouse() {
        Food milk = new Milk("Milk", LocalDateTime.now().plusMonths(3), LocalDateTime.now().minusMonths(8), 77, 8);
        Food apple = new Apple("Apple", LocalDateTime.now().plusMonths(8), LocalDateTime.now().minusMonths(2), 177, 11);
        Food orange = new Orange("Orange", LocalDateTime.now(), LocalDateTime.now().minusMonths(1), 7, 10);
        controlQuality.redefine(milk);
        controlQuality.redefine(apple);
        controlQuality.redefine(orange);
        shop = storeList.get(0);
        warehouse = storeList.get(1);
        trash = storeList.get(2);
        assertThat(shop.findAllFood(), is(List.of(milk)));
        assertThat(warehouse.findAllFood(), is(List.of(orange)));
        assertThat(trash.findAllFood(), is(List.of(orange)));
    }

    @Test
    public void whenResortAllFoods() {
        Food milk = new Milk("Milk", LocalDateTime.now().plusMonths(3), LocalDateTime.now().minusMonths(8), 77, 8);
        controlQuality.redefine(milk);
        shop = storeList.get(0);
        assertThat(shop.findAllFood(), is(List.of(milk)));
        milk.setName("new Milk for resort");
        controlQuality.redefine(milk);
        controlQuality.resort();
        assertThat(shop.findAllFood(), is(List.of(milk)));
    }

}