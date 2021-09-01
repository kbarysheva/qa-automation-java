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
        if ((request.getType().equals(LoanType.PERSON) && request.getAmount() <= 10_000 && request.getMonths() <= 12)
                || (request.getType().equals(LoanType.OOO) && request.getAmount() > 10_000 && request.getMonths() < 12)) {
            return new LoanResponse(requestId, request, ResponseType.APPROVED);
        }
        else if ((request.getType().equals(LoanType.IP))
                || (request.getType().equals(LoanType.PERSON) && request.getAmount() > 10_000 && request.getMonths() > 12)
                || (request.getType().equals(LoanType.OOO))
                    && ((request.getAmount() <= 10_000) || (request.getAmount() > 10_000 && request.getMonths() >= 12)))
            {
                return new LoanResponse(-1, request, ResponseType.DENIED);
            }
        else
            return new LoanResponse(-1, request,ResponseType.UNKNOWN);
    }
}