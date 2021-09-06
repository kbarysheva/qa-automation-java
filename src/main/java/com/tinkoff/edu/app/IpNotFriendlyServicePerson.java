package com.tinkoff.edu.app;

public class IpNotFriendlyServicePerson extends AnyTypeLoanCalcService {
    public IpNotFriendlyServicePerson(LoanCalcRepository repository) {
        super(repository);
    }

    @Override
    public LoanResponse createRequest(LoanRequest request) {
       if (request.getType().equals(LoanType.IP)) {
           return new LoanResponse(-1, request, ResponseType.DENIED);
       }
        return super.createRequest(request);
    }
}
