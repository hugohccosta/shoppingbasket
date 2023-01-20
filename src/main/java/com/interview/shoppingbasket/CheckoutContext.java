package com.interview.shoppingbasket;

import java.util.List;

class CheckoutContext {
    private Basket basket;
    private double retailPriceTotal = 0.0;
    private List<Promotion> promotions;

    public void setRetailPriceTotal(double retailPriceTotal) {
        this.retailPriceTotal = retailPriceTotal;
    }

    CheckoutContext(Basket basket) {
        this.basket = basket;
    }

    public PaymentSummary paymentSummary() {
        return new PaymentSummary(retailPriceTotal);
    }


    public Basket getBasket() {
        return basket;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(final List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion getPromotionProductCode (String productCode) {
        return promotions.stream().filter(promotion ->
                promotion.getProductCode().equals(productCode)).findFirst().orElse(null);
    }
}
