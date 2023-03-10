package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPipeline {

    private List<CheckoutStep> steps = new ArrayList<>();

    public PaymentSummary checkout(final Basket basket) {
        final CheckoutContext checkoutContext = new CheckoutContext(basket);
        for (final CheckoutStep checkoutStep : steps) {
            checkoutStep.execute(checkoutContext);
        }

        return checkoutContext.paymentSummary();
    }

    public void addStep(final CheckoutStep checkoutStep) {
        steps.add(checkoutStep);
    }
}
