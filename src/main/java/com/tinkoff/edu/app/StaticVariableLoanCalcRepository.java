package com.tinkoff.edu.app;

public class StaticVariableLoanCalcRepository implements LoanCalcRepository{
    private static int requestId;

    /**
     * TODO persists request
     * @return RequestId
     */
    @Override
    public int save(LoanRequest request) {
        return ++requestId;
    }
}