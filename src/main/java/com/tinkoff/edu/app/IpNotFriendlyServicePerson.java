package com.tinkoff.edu.app;

public class IpNotFriendlyServicePerson extends PersonLoanCalcService {
    public IpNotFriendlyServicePerson(LoanCalcRepository repository) {
        super(repository);
    }

    @Override
    public int createRequest(LoanRequest request) {
       if (request.getType().equals(LoanType.IP)) return -1;

        return super.createRequest(request);
    }
}
