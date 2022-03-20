package ru.netology.fvd.moneytransferservice.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.fvd.moneytransferservice.model.*;
import ru.netology.fvd.moneytransferservice.service.MoneyTransferService;
import ru.netology.fvd.moneytransferservice.util.ResponseUtil;

@Slf4j
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