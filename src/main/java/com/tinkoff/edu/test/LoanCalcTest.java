package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;
import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.StaticVariableLoanCalcRepository;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(LoanType.IP,10, 1_000);
        LoanCalcController controller = new LoanCalcController(new StaticVariableLoanCalcRepository());
        int requestId = controller.createRequest(request);
        System.out.println("Request: " + request);
        System.out.println(requestId + " must be 1");
    }
}
