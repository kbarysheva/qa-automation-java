package com.tinkoff.edu.app;

public class PersonLoanCalcService implements LoanCalcService {
    private LoanCalcRepository repository;

    public PersonLoanCalcService(LoanCalcRepository repository) {
        this.repository = repository;
    }

    /**
     * TODO Loan calculation
     */
    public int createRequest(LoanRequest request) {
        return repository.save(request);
    }
}