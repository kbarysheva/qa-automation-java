package com.tinkoff.edu.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapLoanCalcRepository implements LoanCalcRepository {

    private final Map<UUID, LoanResponse> responseMap = new HashMap<>();

    public Map<UUID, LoanResponse> getResponseMap() {
        return responseMap;
    }

    public ResponseType getStatusByUUID (Object uuid){
        if (uuid == null) throw new IllegalArgumentException();
        LoanResponse response = responseMap.get(uuid);
        if (response == null) return ResponseType.UNKNOWN;
        return response.getResponseType();
    }

    public boolean setStatusByUUID(Object uuid, ResponseType responseType){
        if (uuid == null || responseType == null) throw new IllegalArgumentException();
        LoanResponse loanResponse = responseMap.get(uuid);
        loanResponse.setResponseType(responseType);
        return true;
    }

    @Override
    public UUID save(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();
        UUID uuid = UUID.randomUUID();
        LoanResponse loanResponse = new LoanResponse(uuid,request,ResponseType.APPROVED);
        responseMap.put(uuid, loanResponse);
        return uuid;
    }
}
