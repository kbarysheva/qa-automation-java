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

        boolean fioTooBig = request.getFio().length() > 100;
        boolean fioTooSmall = request.getFio().length() < 10;

        if (fioTooSmall || fioTooBig || !isOnlyLettersAndHyphensName(request.getFio())) throw new IllegalArgumentException();
        return service.createRequest(request);
    }

    private boolean isOnlyLettersAndHyphensName(String fio) {
        return (fio.matches("^[a-zA-Z-]+$"));
    }
}

