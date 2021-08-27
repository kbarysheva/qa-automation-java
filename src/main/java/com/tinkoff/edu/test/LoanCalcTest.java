package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(LoanType.IP,10, 1_000);
        LoanCalcController controller = new LoanCalcController(new StaticVariableLoanCalcRepository());
        int requestId = controller.createRequest(request);
        LoanResponse loanResponse = new LoanResponse(requestId, request, ResponseType.APPROVED);
        System.out.println("Request: " + request);
        System.out.println("Response: " + loanResponse);
        System.out.println(requestId + " must be 1");
    }
}
