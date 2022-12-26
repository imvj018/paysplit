package com.example.paymentapplication;

public class totlistItem {
    public final String name, amount;

    public totlistItem(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
