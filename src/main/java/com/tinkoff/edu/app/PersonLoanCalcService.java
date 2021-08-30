package com.tinkoff.edu.app;

public class PersonLoanCalcService implements LoanCalcService {
    private LoanCalcRepository repository;

    public PersonLoanCalcService(LoanCalcRepository repository) { // Repo DI
        this.repository = repository;
    }

    /**
     * TODO Loan calculation
     */
    public LoanResponse createRequest(LoanRequest request) {
        int requestId = repository.save(request);
        LoanResponse loanResponse = new LoanResponse(requestId, request, ResponseType.APPROVED);
        return loanResponse;
    }
}