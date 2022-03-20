package ru.netology.fvd.moneytransferservice.controller;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.fvd.moneytransferservice.exceptions.ErrorInputData;
import ru.netology.fvd.moneytransferservice.exceptions.ErrorTransfer;
import ru.netology.fvd.moneytransferservice.model.*;
import ru.netology.fvd.moneytransferservice.service.MoneyTransferService;

@RestController
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Response> createTransferMoneyOperation(@RequestBody TransferMoneyRequest transferMoneyRequest) {
        return moneyTransferService.createTransferMoneyOperation(transferMoneyRequest);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<Response> confirmOperationRequest(@RequestBody ConfirmOperationRequest confirmOperationRequest) {
        return moneyTransferService.confirmTransferMoneyOperation(confirmOperationRequest);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleRTE(RuntimeException e) {
        log.error(e.getMessage());
        Response response = ResponseUtil.getServerErrorResponse();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


}