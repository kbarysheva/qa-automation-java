package com.tinkoff.edu.app;

public class LoanCalcController {
    private LoanCalcService service; // Creator

    public LoanCalcController(LoanCalcService service) { // Service DI
        this.service = service;
    }

    /**
     * TODO Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();
        return service.createRequest(request);
    }
}

