package com.tinkoff.edu.app;

public class AnyTypeLoanCalcService implements LoanCalcService {
    private LoanCalcRepository repository;

    public AnyTypeLoanCalcService(LoanCalcRepository repository) { // Repo DI
        this.repository = repository;
    }

    /**
     * TODO Loan calculation
     */
    public LoanResponse createRequest(LoanRequest request) {
        int requestId = repository.save(request);
        boolean case1 = request.getType().equals(LoanType.PERSON) && request.getAmount() <= 10_000 && request.getMonths() <= 12;
        boolean case2 = request.getType().equals(LoanType.OOO) && request.getAmount() > 10_000 && request.getMonths() < 12;
        boolean case3 = request.getType().equals(LoanType.IP);
        boolean case4 = request.getType().equals(LoanType.PERSON) && request.getAmount() > 10_000 && request.getMonths() > 12;
        boolean case5 = request.getType().equals(LoanType.OOO) && request.getAmount() <= 10_000;
        boolean case6 = request.getType().equals(LoanType.OOO) && request.getAmount() > 10_000 && request.getMonths() >= 12;

        if (case1 || case2) {
            return new LoanResponse(requestId, request, ResponseType.APPROVED);
        }
        else if (case3 || case4 || case5 || case6) {
            return new LoanResponse(-1, request, ResponseType.DENIED);
        }
        else
            return new LoanResponse(-1, request,ResponseType.UNKNOWN);
    }
}