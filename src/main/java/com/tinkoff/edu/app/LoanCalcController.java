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
        return service.createRequest(request);
    }
}

