package com.interview.shoppingbasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.when;

class PromotionCheckoutStepTest {

    private PromotionsService promotionsService;
    private CheckoutContext checkoutContext;
    private Basket basket;

    @BeforeEach
    void setup() {
        promotionsService = Mockito.mock(PromotionsService.class);
        checkoutContext = Mockito.mock(CheckoutContext.class);
        basket = new Basket();

        when(checkoutContext.getBasket()).thenReturn(basket);
    }

    @Test
    void setEmptyPromotions() {

        final PromotionCheckoutStep promotionCheckoutStep = new PromotionCheckoutStep(promotionsService);

        promotionCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).setPromotions(Collections.emptyList());
    }

    @Test
    void setPromotionOfProductToBasketItem() {

        basket.add("product1", "myproduct1", 10);
        basket.add("product2", "myproduct2", 10);

        final Promotion promotion = new Promotion("product1", "halfPrice");
        when(checkoutContext.getPromotionProductCode("prodcut1")).thenReturn(promotion);

        when(checkoutContext.getBasket()).thenReturn(basket);
        when(promotionsService.getPromotions(basket)).thenReturn(Collections.singletonList(promotion));

        final PromotionCheckoutStep promotionCheckoutStep = new PromotionCheckoutStep(promotionsService);
        promotionCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).setPromotions(Collections.singletonList(promotion));

    }

}
