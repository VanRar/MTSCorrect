package ru.netology.fvd.moneytransferservice.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netology.fvd.moneytransferservice.model.*;


@Service
public class MoneyTransferService {
    private final OperationService operationService;
    private final UserAccountService userAccountService;

    public MoneyTransferService(OperationService operationService, UserAccountService userAccountService) {
        this.operationService = operationService;
        this.userAccountService = userAccountService;
    }

    public ResponseEntity<Response> createTransferMoneyOperation(TransferMoneyRequest transferMoneyRequest) {
        Response response = userAccountService.validateTransferMoneyRequest(transferMoneyRequest);

        if (!HttpStatus.BAD_REQUEST.equals(response.getHttpStatus())) {
            Operation operation = operationService.createOperation(transferMoneyRequest);
            response.setOperationId(operation.getOperationId());
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    public ResponseEntity<Response> confirmTransferMoneyOperation(ConfirmOperationRequest confirmOperationRequest) {
        Response response = operationService.confirmOperationRequest(confirmOperationRequest);

        if (!HttpStatus.BAD_REQUEST.equals(response.getHttpStatus())) {
            String operationId = confirmOperationRequest.getOperationId();
            Operation operation = operationService.getOperationData(operationId);

            response = userAccountService.transferMoney(operation);
            operationService.removeOperation(operationId);
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
