package com.interview.shoppingbasket;

import java.util.Optional;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(final PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(final CheckoutContext checkoutContext) {
        final Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;

        for (final BasketItem basketItem: basket.getItems()) {
            final int quantity = basketItem.getQuantity();
            final double price = pricingService.getPrice(basketItem.getProductCode());

            basketItem.setProductRetailPrice(price);

            final Optional<Promotion> optionalPromotion = checkoutContext.getPromotions().stream()
                    .filter(promotion1 -> promotion1.getProductCode()
                            .equalsIgnoreCase(basketItem.getProductCode())).findFirst();
            if (optionalPromotion.isPresent()) {
                retailTotal += applyPromotion(optionalPromotion.get(), basketItem, price);
            } else {
                retailTotal += quantity*price;
            }
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(final Promotion promotion, final BasketItem item, final double price) {
        /*
         * Implement applyPromotion method
         */
        return promotion.calculate(item, price);
    }
}
