package com.interview.shoppingbasket;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BasketConsolidationCheckoutStepTest {

    @Test
    void basketConsolidationCheckoutStepTest() {

        final CheckoutContext checkoutContext = Mockito.mock(CheckoutContext.class);
        final Basket basket = Mockito.mock(Basket.class);

        when(checkoutContext.getBasket()).thenReturn(basket);

        final BasketConsolidationCheckoutStep basketConsolidationCheckoutStep = new BasketConsolidationCheckoutStep();
        basketConsolidationCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).getBasket();

        Mockito.verify(basket).consolidateItems();
    }

}
