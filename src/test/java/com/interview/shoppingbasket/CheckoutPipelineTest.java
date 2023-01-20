package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;

    @Mock
    Basket basket;

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;

    private PricingService pricingService;
    private PromotionsService promotionsService;

    private CheckoutContext checkoutContext;

    @BeforeEach
    void setup() {
        pricingService = Mockito.mock(PricingService.class);
        promotionsService = Mockito.mock(PromotionsService.class);
        checkoutContext = Mockito.mock(CheckoutContext.class);
        checkoutStep1 = new BasketConsolidationCheckoutStep();
        checkoutStep2 = new RetailPriceCheckoutStep(pricingService);
        basket = new Basket();

        checkoutPipeline = new CheckoutPipeline();

    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        final PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals( 0.0, paymentSummary.getRetailTotal());
    }

    @Test
    void executeAllPassedCheckoutSteps() {
        basket.add("product1", "myProduct", 10);
        basket.add("product1", "myProduct", 12);
        basket.add("product2", "myProduct2", 10);
        basket.add("product3", "myProduct3", 10);
        basket.add("product3", "myProduct3", 1);
        basket.add("product2", "myProduct2", 4);
        basket.add("product3", "myProduct3", 1);
        basket.add("product1", "myProduct", 11);
        basket.add("product3", "myProduct3", 1);

        Assertions.assertEquals(9, basket.getItems().size());

        basket.consolidateItems();

        Assertions.assertEquals(3, basket.getItems().size());

        when(checkoutContext.getBasket()).thenReturn(basket);

        when(pricingService.getPrice("product1")).thenReturn(3.99);
        when(pricingService.getPrice("product2")).thenReturn(2.0);
        when(pricingService.getPrice("product3")).thenReturn(4.50);

        final List<Promotion> promotions = new ArrayList<>();
        final Promotion promotion = new Promotion("product1", "halfPrice");
        final Promotion promotion2 = new Promotion("product2", "twoForOne");
        final Promotion promotion3 = new Promotion("product3", "tenPercent");
        promotions.add(promotion);
        promotions.add(promotion2);
        promotions.add(promotion3);
        when(checkoutContext.getPromotions()).thenReturn(promotions);

        when(checkoutContext.getPromotionProductCode("product1")).thenReturn(promotions.get(0));

        when(checkoutContext.getPromotionProductCode("product2")).thenReturn(promotions.get(1));
        when(checkoutContext.getPromotionProductCode("product3")).thenReturn(promotions.get(2));
        checkoutStep1.execute(checkoutContext);

        checkoutStep2.execute(checkoutContext);
        Mockito.verify(checkoutContext).setRetailPriceTotal(((3.99*33)/2)+Math.ceil((14/2))*2+(4.50*13*0.9));

    }

}
