package com.interview.shoppingbasket;

import java.util.List;

public class PromotionCheckoutStep implements CheckoutStep {

    private final PromotionsService promotionsService;

    public PromotionCheckoutStep(final PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @Override
    public void execute(final CheckoutContext checkoutContext) {
        final Basket basket = checkoutContext.getBasket();
        final List<Promotion> promotions = promotionsService.getPromotions(basket);

        checkoutContext.setPromotions(promotions);
    }
    // Not yet implemented
}
