package com.tinkoff.edu.app;

/**
 *
 */
public class LoanCalcController {
    /**
     * TODO Validates and logs request
     */
    public static int createRequest() {
        LoanCalcLogger.log();
        return LoanCalcService.createRequest();
    }
}
