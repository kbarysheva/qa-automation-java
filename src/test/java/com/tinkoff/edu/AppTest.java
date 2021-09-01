package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init(){
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,12_000, 11);
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new VariableLoanCalcRepository()));
        //endregion
    }

    @Test
    @DisplayName("Проверка requestId = 1 после выполнения первого запроса")
    public void shouldGet1WhenFirstRequest() {
        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request); // factual
        //endregion

        //region Assert / Then
        assertEquals(1 ,loanResponse.getRequestId());
        //endregion
    }

    @Test
    @DisplayName("Проверка requestId после подмены стартового значения")
    public void shouldGetIncrementedIdWhenAnyCall() {
        //region Fixture / Arrange / Given
        final int startRequestId = 4;
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new VariableLoanCalcRepository(startRequestId)));
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(startRequestId + 1 ,loanResponse.getRequestId());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType APPROVED, когда LoanType PERSON Amount < 10_000.0 Month < 12")
    public void shouldGetApprovedCase1() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,9_000, 10);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.APPROVED, loanResponse.getResponseType());
        //endregion
    }
    @Test
    @DisplayName("Проверка ResponseType APPROVED, когда LoanType PERSON Amount = 10_000.0 Month = 12")
    public void shouldGetApprovedCase1_1() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.APPROVED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType DENIED, когда LoanType PERSON Amount > 10_000.0 Month > 12")
    public void shouldGetApprovedCase2() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,11_000, 13);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType DENIED, когда LoanType OOO Amount < 10_000.0 Month any ")
    public void shouldGetApprovedCase3() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,9_000, 2);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType DENIED, когда LoanType OOO Amount = 10_000 Month any ")
    public void shouldGetApprovedCase3_1() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,10_000, 75);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType APPROVED, когда LoanType OOO Amount > 10_000 Month < 12 ")
    public void shouldGetApprovedCase4() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,14_000, 1);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.APPROVED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType DENIED, когда LoanType OOO Amount > 10_000 Month > 12 ")
    public void shouldGetApprovedCase6() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,11_000, 14);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType DENIED, когда LoanType OOO Amount > 10_000 Month = 12 ")
    public void shouldGetApprovedCase6_1() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,26_000, 12);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка  ResponseType DENIED, когда LoanType IP")
    public void shouldGetApprovedCase7() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.IP,26_000, 12);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.DENIED, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка ResponseType UNKNOWN")
    public void shouldGetApprovedCase8() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 13);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.UNKNOWN, loanResponse.getResponseType());
        //endregion
    }
}
