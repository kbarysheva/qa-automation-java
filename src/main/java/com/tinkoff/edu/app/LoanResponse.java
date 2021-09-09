package com.tinkoff.edu.app;

public class LoanResponse {
    private final Object requestId;
    private final LoanRequest request;
    private ResponseType responseType;

    public LoanResponse(Object requestId, LoanRequest request, ResponseType responseType){
        this.requestId = requestId;
        this.request = request;
        this.responseType = responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getRequestId() {
        return requestId;
    }
    public LoanRequest getRequest() {
        return request;
    }
    public String toString() {
        return "Response: {"
                + this.getRequestId() + ","
                + this.getRequest() + ","
                + this.getResponseType() +
                "}";
    }
}
