package com.interview.shoppingbasket;

public class Promotion {
    public static final double HALF_PRICE = 0.5;
    public static final double TEN_PERC = 0.9;
    public static final double TWO = 2.0;
    // Not yet implemented

    private String productCode;

    private String value;

    public Promotion(final String productCode, final String value) {
        this.productCode = productCode;
        this.value = value;
    }

    public double calculate (final BasketItem item, final double price) {
        if ("halfPrice".equalsIgnoreCase(this.value)) {
            return item.getQuantity()*price* HALF_PRICE;
        }
        if ("tenPercent".equalsIgnoreCase(this.value)) {
            return item.getQuantity()*price* TEN_PERC;
        }
        if ("twoForOne".equalsIgnoreCase(this.value)) {
            return Math.ceil(item.getQuantity()/ TWO)*price;
        }

        return item.getQuantity()*price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(final String productCode) {
        this.productCode = productCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
