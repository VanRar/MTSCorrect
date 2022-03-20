package ru.netology.fvd.moneytransferservice.model;

public class Operation {
    private String operationId;
    private String code;
    private TransferMoneyRequest transferMoneyRequest;

    public Operation(String operationId) {

    }

    public Operation setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public Operation setCode(String code) {
        this.code = code;
        return this;
    }

    public Operation setTransferMoneyRequest(TransferMoneyRequest transferMoneyRequest) {
        this.transferMoneyRequest = transferMoneyRequest;
        return this;
    }

    public String getLog() {
        return " operationId: " + operationId +
                " card from: " + transferMoneyRequest.getCardFromNumber() +
                " card to: " + transferMoneyRequest.getCardToNumber() +
                " amount: " + transferMoneyRequest.getAmount().getValue() +
                " currency: " + transferMoneyRequest.getAmount().getCurrency();
    }
}