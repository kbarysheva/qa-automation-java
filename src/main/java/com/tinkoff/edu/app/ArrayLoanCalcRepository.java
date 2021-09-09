package com.tinkoff.edu.app;

import java.util.UUID;

public class ArrayLoanCalcRepository implements LoanCalcRepository{

    private LoanResponse[] responseArray = new LoanResponse[10_000];

    public LoanResponse[] getResponseArray() {
        return responseArray;
    }

    int responseArrayPosition = 0;

    public ResponseType getStatusByUUID (Object uuid) {
        for (int i = 0; i < responseArray.length; i++) {
            if (responseArray[i]==null) return ResponseType.UNKNOWN;
            if (responseArray[i].getRequestId().equals(uuid)) {
                return responseArray[i].getResponseType();
            }
        }
        return ResponseType.UNKNOWN;
    }

    public boolean setStatusByUUID (Object uuid, ResponseType responseType) {
        for (int i = 0; i < responseArray.length; i++) {
            if (responseArray[i]==null) return false;
            if (responseArray[i].getRequestId().equals(uuid)) {
                responseArray[i].setResponseType(responseType);
                return true;
            }
        }
        return false;
    }

    @Override
    public UUID save(LoanRequest request) {
        UUID uuid = UUID.randomUUID();
        responseArray[responseArrayPosition++] = new LoanResponse(uuid,request,ResponseType.APPROVED);
        return uuid;
    }

}
