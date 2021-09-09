package com.tinkoff.edu.app;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final LoanType type;
    private final int months;
    private final int amount;
    private final String fio;

    public LoanRequest(LoanType type, int amount, int months) {
        this.type = type;
        this.months = months;
        this.amount = amount;
        this.fio = "unknown";
    }

    public LoanRequest(LoanType type, int amount, int months, String fio) {
        this.type = type;
        this.months = months;
        this.amount = amount;
        this.fio = fio;
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

    public String getFio() {
        return fio;
    }

    public String toString() {
        return "Request: {"
                + this.type + ","
                + this.getAmount()
                + " for " + this.getMonths()
                + ", "
                + this.fio +
                "}";
    }

}