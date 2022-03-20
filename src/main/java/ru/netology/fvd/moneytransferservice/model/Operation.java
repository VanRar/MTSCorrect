package ru.netology.fvd.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operation {
    private String operationId;
    private String code;
    private TransferMoneyRequest transferMoneyRequest;

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