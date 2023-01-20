package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        final Map<String, Integer> itens = new HashMap<>(1);
        final List<BasketItem> basketItems = this.items;
        final List<BasketItem> temp = new ArrayList<>();

        for (int i = 0; i < basketItems.size(); i++) {
            final BasketItem basketItem = basketItems.get(i);
            final Integer index = itens.get(basketItem.getProductCode());
            if (null != index) {
                final BasketItem basketItem1 = temp.get(index);
                int quantity = basketItem1.getQuantity();
                quantity += basketItem.getQuantity();
                basketItem1.setQuantity(quantity);
            } else {
                temp.add(basketItem);
                itens.put(basketItem.getProductCode(), temp.lastIndexOf(basketItem));
            }
        }

        this.items = temp;
    }
}
