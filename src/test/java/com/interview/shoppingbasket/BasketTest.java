package com.interview.shoppingbasket;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {
    @Test
    void emptyBasket() {
        final Basket basket = new Basket();
        final List<BasketItem> basketSize = basket.getItems();

        Assertions.assertEquals(0, basketSize.size());
    }

    @Test
    void createBasketFullConstructor() {
        final Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        final List<BasketItem> basketSize = basket.getItems();

        Assertions.assertEquals(1, basketSize.size());
        Assertions.assertEquals("productCode", basketSize.get(0).getProductCode());
        Assertions.assertEquals("myProduct", basketSize.get(0).getProductName());
        Assertions.assertEquals(10, basketSize.get(0).getQuantity());
    }

    @Test
    void createBasketWithMultipleProducts() {
        final Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);

        final List<BasketItem> basketSize = basket.getItems();

        Assertions.assertEquals(3, basketSize.size());
        Assertions.assertEquals("productCode", basketSize.get(0).getProductCode());
        Assertions.assertEquals("myProduct", basketSize.get(0).getProductName());
        Assertions.assertEquals(10, basketSize.get(0).getQuantity());
        Assertions.assertEquals("productCode2", basketSize.get(1).getProductCode());
        Assertions.assertEquals("myProduct2", basketSize.get(1).getProductName());
        Assertions.assertEquals(10, basketSize.get(1).getQuantity());
        Assertions.assertEquals("productCode3", basketSize.get(2).getProductCode());
        Assertions.assertEquals("myProduct3", basketSize.get(2).getProductName());
        Assertions.assertEquals(10, basketSize.get(2).getQuantity());
    }

    @Test
    void consolidateBasketTest() {
        // Exercise - implement the unit test for consolidate items
        final Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode", "myProduct", 12);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);
        basket.add("productCode3", "myProduct3", 1);
        basket.add("productCode2", "myProduct2", 4);
        basket.add("productCode3", "myProduct3", 1);
        basket.add("productCode", "myProduct", 11);
        basket.add("productCode3", "myProduct3", 1);


        Assertions.assertEquals(9, basket.getItems().size());

        basket.consolidateItems();

        Assertions.assertEquals(3, basket.getItems().size());

        final List<BasketItem> basketSize = basket.getItems();

        Assertions.assertEquals("productCode", basketSize.get(0).getProductCode());
        Assertions.assertEquals("myProduct", basketSize.get(0).getProductName());
        Assertions.assertEquals(33, basketSize.get(0).getQuantity());
        Assertions.assertEquals("productCode2", basketSize.get(1).getProductCode());
        Assertions.assertEquals("myProduct2", basketSize.get(1).getProductName());
        Assertions.assertEquals(14, basketSize.get(1).getQuantity());
        Assertions.assertEquals("productCode3", basketSize.get(2).getProductCode());
        Assertions.assertEquals("myProduct3", basketSize.get(2).getProductName());
        Assertions.assertEquals(13, basketSize.get(2).getQuantity());
    }
}
