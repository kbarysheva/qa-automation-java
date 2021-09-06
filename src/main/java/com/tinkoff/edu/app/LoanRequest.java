package com.tinkoff.edu.app;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final LoanType type;
    private final int months;
    private final int amount;

    public LoanRequest(LoanType type, int amount, int months) {
        this.type = type;
        this.months = months;
        this.amount = amount;
    }

    public LoanType getType() {
        return type;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return "Request: {"
                + this.type + ","
                + this.getAmount()
                + " for " + this.getMonths() +
                "}";
    }

}