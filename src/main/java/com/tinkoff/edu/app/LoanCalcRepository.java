package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId;

    /**
     * TODO persists request
     * @return RequestId
     */
    public int save(LoanRequest request) {
        return ++requestId;
    }
}