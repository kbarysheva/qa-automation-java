package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    /**
     * TODO Validates and logs request
     */
    public int createRequest(LoanRequest request) {

        log(request);
        LoanCalcService service = new LoanCalcService();
        return service.createRequest(request);
    }
}

