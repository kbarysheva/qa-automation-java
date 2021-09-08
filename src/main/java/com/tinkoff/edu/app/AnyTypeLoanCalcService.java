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
        if (request == null) throw new IllegalArgumentException();
        switch(request.getType()){
                case IP:
                    return new LoanResponse(-1, request, ResponseType.DENIED);
                case OOO:
                    if (request.getAmount() > 10_000 && request.getMonths() < 12) {
                        return new LoanResponse(repository.save(request), request, ResponseType.APPROVED);
                    } else return new LoanResponse(-1, request, ResponseType.DENIED);
                case PERSON:
                    if (request.getAmount() <= 10_000 && request.getMonths() <= 12) {
                        return new LoanResponse(repository.save(request), request, ResponseType.APPROVED);
                    } else if (request.getAmount() > 10_000 && request.getMonths() > 12) {
                        return new LoanResponse(-1, request, ResponseType.DENIED);
                    } else return new LoanResponse(-1, request, ResponseType.UNKNOWN);
                default:
                    return new LoanResponse(-1, request, ResponseType.UNKNOWN);
        }
    }
}