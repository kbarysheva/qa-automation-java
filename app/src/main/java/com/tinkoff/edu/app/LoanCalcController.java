package com.tinkoff.edu.app;

/**
 *
 */
public class LoanCalcController {
    /**
     * TODO Validates and logs request
     */
    public static int createRequest() {
        int localVar;

        //param validation
        //log request
        LoanCalcLogger.log();
        return LoanCalcService.createRequest();
    }
}
