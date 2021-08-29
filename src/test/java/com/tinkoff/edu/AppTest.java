package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init(){
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,10, 1_000);
        sut = new LoanCalcController(new IpNotFriendlyServicePerson(new VariableLoanCalcRepository()));
        //endregion
    }

    @Test
    @DisplayName("Проверка requestId = 1 после выполнения первого запроса")
    public void shouldGet1WhenFirstRequest() {
        //region Fixture / Arrange / Given
        sut = new LoanCalcController(new IpNotFriendlyServicePerson(new VariableLoanCalcRepository()));
        //endregion

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
        sut = new LoanCalcController(new IpNotFriendlyServicePerson(new VariableLoanCalcRepository(startRequestId)));
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(startRequestId + 1 ,loanResponse.getRequestId());
        //endregion
    }
}
