package com.tinkoff.edu.app;

public class LoanCalcService {
    /**
     * TODO Loan calculation
     */
    public int createRequest(LoanRequest request) {
        LoanCalcRepository repository = new LoanCalcRepository();
        return repository.save(request);
    }
}