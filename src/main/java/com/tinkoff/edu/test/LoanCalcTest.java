package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(LoanType.IP,10, 1_000);
        LoanCalcController controller = new LoanCalcController(new IpNotFriendlyServicePerson(new StaticVariableLoanCalcRepository()));
        LoanResponse loanResponse = controller.createRequest(request);
        //LoanResponse loanResponse = new LoanResponse(requestId, request, ResponseType.APPROVED);
        System.out.println("Request: " + request);
        System.out.println("Response: " + loanResponse);
        System.out.println(loanResponse.getRequestId() + " must be 1");
    }
}
