package com.tinkoff.edu.app;

public class LoanCalcController {
    private PersonLoanCalcService service; // Creator

    public LoanCalcController(LoanCalcRepository repository) {
        service = new IpNotFriendlyServicePerson(repository);
    }

    /**
     * TODO Validates and logs request
     */
    public int createRequest(LoanRequest request) {
        return service.createRequest(request);
    }
}

