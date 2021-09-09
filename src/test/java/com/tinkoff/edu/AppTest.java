package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Проверка APPROVED, когда LoanType PERSON Amount < 10_000.0 Month < 12")
    public void shouldGetApprovedWhenPerson() {
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
    @DisplayName("Проверка APPROVED, когда LoanType PERSON Amount = 10_000.0 Month = 12")
    public void shouldGetApprovedWhenPerson_2() {
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
    @DisplayName("Проверка DENIED, когда LoanType PERSON Amount > 10_000.0 Month > 12")
    public void shouldGetDeniedWhenPerson() {
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
    @DisplayName("Проверка DENIED, когда LoanType OOO Amount < 10_000.0 Month any")
    public void shouldGetDeniedWhenOOO() {
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
    @DisplayName("Проверка DENIED, когда LoanType OOO Amount = 10_000 Month any")
    public void shouldGetDeniedWHenOOO() {
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
    @DisplayName("Проверка APPROVED, когда LoanType OOO Amount > 10_000 Month < 12 ")
    public void shouldGetApprovedWhenOOO() {
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
    @DisplayName("Проверка DENIED, когда LoanType OOO Amount > 10_000 Month > 12 ")
    public void shouldGetDeniedWhenOOO_2() {
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
    @DisplayName("Проверка DENIED, когда LoanType OOO Amount > 10_000 Month = 12 ")
    public void shouldGetDeniedWhenOOO_3() {
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
    @DisplayName("Проверка DENIED, когда LoanType IP")
    public void shouldGetDeniedWhenIp() {
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
    @DisplayName("Проверка DENIED, когда LoanType IP с использованием IpNotFriendlyServicePerson")
    public void shouldGetDeniedWhenIpNotFriendlyService() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.IP,26_000, 12);
        sut = new LoanCalcController(new IpNotFriendlyServicePerson(new VariableLoanCalcRepository()));
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
    @DisplayName("Проверка APPROVE, когда LoanType PERSON с использованием IpNotFriendlyServicePerson")
    public void shouldGetApproveWhenIpNotFriendlyService() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,5_000, 5);
        sut = new LoanCalcController(new IpNotFriendlyServicePerson(new VariableLoanCalcRepository()));
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
    @DisplayName("Проверка ResponseType UNKNOWN, когда LoanType PERSON Amount = 10_000 months > 12")
    public void shouldGetUnknownInCornerCase() {
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

    @Test
    @DisplayName("Проверка ResponseType UNKNOWN, когда LoanType PERSON Amount > 10_000 Months < 12")
    public void shouldGetUnknownInCornerCase2() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,13_000, 10);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(-1 ,loanResponse.getRequestId());
        assertEquals(ResponseType.UNKNOWN, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка LoanResponse toString")
    public void shouldGetStringInLoanResponse() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 13);
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        String expectedResponseString = "Response: {-1,Request: {"
                + LoanType.PERSON
                + ","
                + 10000
                + " for "
                + 13
                + ", unknown-unknown},"
                + ResponseType.UNKNOWN
                + "}";

        assertEquals(expectedResponseString, loanResponse.toString());
        //endregion
    }

    @Test
    @DisplayName("Проверка LoanRequest toString")
    public void shouldGetStringInLoanRequest() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,18_500, 5);
        //endregion

        //region Assert / Then
        String expectedResponseString = "Request: {"
                + LoanType.OOO
                + ","
                + 18_500
                + " for "
                + 5
                + ", unknown-unknown}";
        assertEquals(expectedResponseString, request.toString());
        //endregion
    }

    @Test
    @DisplayName("Проверка LoanRequest toString c ФИО")
    public void shouldGetStringInLoanRequestWithFio() {
        //region Fixture / Arrange / Given
        String fio = "Ivanov-Ivan-Ivanovich";
        request = new LoanRequest(LoanType.OOO,18_500, 5, fio);
        //endregion

        //region Assert / Then
        String expectedResponseString = "Request: {"
                + LoanType.OOO
                + ","
                + 18_500
                + " for "
                + 5
                + ", "
                + fio
                + "}";
        assertEquals(expectedResponseString, request.toString());
        //endregion
    }

    @Test
    @DisplayName("Проверка возвращаемого типа UUID")
    public void shouldGetUUID() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,18_500, 5);
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new ArrayLoanCalcRepository()));
        UUID uuid = UUID.randomUUID();
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(uuid.getClass(), loanResponse.getRequestId().getClass());
        //endregion
    }

    @Test
    @DisplayName("Проверка сохранения response в массив")
    public void shouldSaveInArray() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.OOO,18_500, 5);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertNotEquals(null, array[0]);
        assertNotEquals(null, array[1]);
        assertNull(array[2]);
        //endregion

    }

    @Test
    @DisplayName("Проверка не сохранения IP в массив")
    public void shouldNotSaveInArray() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.IP,18_500, 5);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertNull(array[0]);
        //endregion
    }

    @Test
    @DisplayName("Проверка получения статуса заявки по UUID")
    public void shouldGetStatusForUUID() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        Object uuid = array[0].getRequestId();
        //endregion

        //region Assert / Then
        assertEquals(ResponseType.APPROVED, repo.getStatusByUUID(uuid));
        //endregion
    }

    @Test
    @DisplayName("Проверка получения статуса заявки по несуществующему UUID")
    public void shouldGetStatusForNonExistentUUID() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        Object uuid = UUID.randomUUID();
        //endregion

        //region Assert / Then
        assertEquals(ResponseType.UNKNOWN, repo.getStatusByUUID(uuid));
        //endregion
    }

    @Test
    @DisplayName("Проверка получения статуса заявки по несуществующему UUID при полном заполнении массива")
    public void shouldGetStatusForNonExistentUUIDInFullArray() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        for (int i = 0; i < array.length; i++) {
            sut.createRequest(request);
        }
        Object uuid = UUID.randomUUID();
        //endregion

        //region Assert / Then
        assertEquals(ResponseType.UNKNOWN, repo.getStatusByUUID(uuid));
        //endregion
    }

    @Test
    @DisplayName("Проверка успешного изменения заявки")
    public void shouldSuccessUpdateResponseType() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        Object uuid = array[0].getRequestId();
        //endregion

        //region Assert / Then
        assertTrue(repo.setStatusByUUID(uuid, ResponseType.DENIED));
        assertEquals(ResponseType.DENIED, repo.getStatusByUUID(uuid));
        //endregion
    }

    @Test
    @DisplayName("Проверка неуспешного обновления заявки при несуществующем UUID")
    public void shouldFailUpdateResponseType() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        sut.createRequest(request);
        Object uuid = UUID.randomUUID();
        //endregion

        //region Assert / Then
        assertFalse(repo.setStatusByUUID(uuid, ResponseType.APPROVED));
        //endregion
    }

    @Test
    @DisplayName("Проверка неуспешного обновления заявки при несуществующем UUID при полном заполнении массива")
    public void shouldFailUpdateResponseTypeInFullArray() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.PERSON,10_000, 12);
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        sut = new LoanCalcController(new AnyTypeLoanCalcService(repo));
        LoanResponse[] array = repo.getResponseArray();
        //endregion

        //region Act / When
        for (int i = 0; i < array.length; i++) {
            sut.createRequest(request);
        }
        Object uuid = UUID.randomUUID();
        //endregion

        //region Assert / Then
        assertFalse(repo.setStatusByUUID(uuid, ResponseType.APPROVED));
        //endregion
    }

    @Test
    @DisplayName("Проверка получения статуса UNKNOWN, когда LoanType UNKNOWN")
    public void shouldGetUnknownWhereLoanTypeUnknown() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanType.UNKNOWN,10_000, 12);
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new ArrayLoanCalcRepository()));
        //endregion

        //region Act / When
        LoanResponse loanResponse = sut.createRequest(request);
        //endregion

        //region Assert / Then
        assertEquals(ResponseType.UNKNOWN, loanResponse.getResponseType());
        //endregion
    }

    @Test
    @DisplayName("Проверка получения ФИО")
    public void shouldGetFio() {
        //region Fixture / Arrange / Given
        String fio = "Ivan-Ivanovich-Ivanov";
        request = new LoanRequest(LoanType.UNKNOWN,10_000, 12, fio);
        //endregion

        //region Assert / Then
        assertEquals(fio, request.getFio());
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при request == null в LoanCalcController createRequest")
    public void shouldGetIAExceptionFromNullInLoanCalcController() {
        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> sut.createRequest(null));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при request == null в IpNotFriendlyServicePerson")
    public void shouldGetIAExceptionFromNullInIpNotFriendlyServicePerson() {
        //region Fixture / Arrange / Given
        IpNotFriendlyServicePerson service = new IpNotFriendlyServicePerson(new VariableLoanCalcRepository());
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> service.createRequest(null));
        //endregion
    }
    @Test
    @DisplayName("Проверка IllegalArgumentException при request == null в AnyTypeLoanCalcService")
    public void shouldGetIAExceptionFromNullInAnyTypeLoanCalcService() {
        //region Fixture / Arrange / Given
        AnyTypeLoanCalcService service = new AnyTypeLoanCalcService(new VariableLoanCalcRepository());
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> service.createRequest(null));
        //endregion
    }
    @Test
    @DisplayName("Проверка IllegalArgumentException при request == null в ArrayLoanCalcRepository save")
    public void shouldGetIAExceptionFromNullInArrayLoanCalcRepositorySave() {
        //region Fixture / Arrange / Given
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> repo.save(null));
        //endregion
    }
    @Test
    @DisplayName("Проверка IllegalArgumentException при uuid == null в ArrayLoanCalcRepository getStatusByUUID")
    public void shouldGetIAExceptionFromNullInArrayLoanCalcRepositoryGetStatusByUUID() {
        //region Fixture / Arrange / Given
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> repo.getStatusByUUID(null));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при uuid == null в ArrayLoanCalcRepository setStatusByUUID")
    public void shouldGetIAExceptionFromNullUUIDInArrayLoanCalcRepositorySetStatusByUUID() {
        //region Fixture / Arrange / Given
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> repo.setStatusByUUID(null, ResponseType.APPROVED));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при responseType == null в ArrayLoanCalcRepository setStatusByUUID")
    public void shouldGetIAExceptionFromNullResponseTypeInArrayLoanCalcRepositorySetStatusByUUID() {
        //region Fixture / Arrange / Given
        ArrayLoanCalcRepository repo = new ArrayLoanCalcRepository();
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> repo.setStatusByUUID(UUID.randomUUID(), null));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при request == null в VariableLoanCalcRepository save")
    public void shouldGetIAExceptionFromNullInVariableLoanCalcRepositorySave() {
        //region Fixture / Arrange / Given
        VariableLoanCalcRepository repo = new VariableLoanCalcRepository();
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> repo.save(null));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при Большом ФИО в LoanCalcController createRequest")
    public void shouldGetIAExceptionFromBigFIOInLoanCalcController() {
        //region Fixture / Arrange / Given
        String bigFio = "IvanIvanovichIvanovIvanIvanovichIvanovIvanIvanovichIvanovIvanIvanovichIvanovIvanIvanovichIvanovIvanIvanovichIvanov";
        request = new LoanRequest(LoanType.PERSON,10_000, 12, bigFio);
        //endregion

        //region Act / When
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new ArrayLoanCalcRepository()));
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> sut.createRequest(request));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при Маленьком ФИО в LoanCalcController createRequest")
    public void shouldGetIAExceptionFromSmallFIOInLoanCalcController() {
        //region Fixture / Arrange / Given
        String smallFio = "Ivanovich";
        request = new LoanRequest(LoanType.PERSON,10_000, 12, smallFio);
        //endregion

        //region Act / When
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new ArrayLoanCalcRepository()));
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> sut.createRequest(request));
        //endregion
    }

    @Test
    @DisplayName("Проверка IllegalArgumentException при лишних символах в LoanCalcController createRequest")
    public void shouldGetIAExceptionFromWrongSymbolsInLoanCalcController() {
        //region Fixture / Arrange / Given
        String wrongFio = "Ivan*Иvanovich";
        request = new LoanRequest(LoanType.PERSON,10_000, 12, wrongFio);
        //endregion

        //region Act / When
        sut = new LoanCalcController(new AnyTypeLoanCalcService(new ArrayLoanCalcRepository()));
        //endregion

        //region Assert / Then
        assertThrows(IllegalArgumentException.class,
                () -> sut.createRequest(request));
        //endregion
    }
}