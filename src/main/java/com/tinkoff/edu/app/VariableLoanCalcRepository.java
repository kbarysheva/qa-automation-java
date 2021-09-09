package com.tinkoff.edu.app;

public class VariableLoanCalcRepository implements LoanCalcRepository{
    private int requestId;

    public VariableLoanCalcRepository() {
        this.requestId = 0;
    }

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    /**
     * TODO persists request
     * @return RequestId
     */
    @Override
    public Integer save(LoanRequest request) {
        return ++requestId;
    }
}