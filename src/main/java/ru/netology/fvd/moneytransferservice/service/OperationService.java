package ru.netology.fvd.moneytransferservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.netology.fvd.moneytransferservice.model.ConfirmOperationRequest;
import ru.netology.fvd.moneytransferservice.model.Operation;
import ru.netology.fvd.moneytransferservice.model.Response;
import ru.netology.fvd.moneytransferservice.model.TransferMoneyRequest;
import ru.netology.fvd.moneytransferservice.repository.MoneyTransferRepository;
import ru.netology.fvd.moneytransferservice.util.IdCodeUtil;
import ru.netology.fvd.moneytransferservice.util.LoggerUtil;
import ru.netology.fvd.moneytransferservice.util.ResponseUtil;


@Slf4j
@Service
public class OperationService {
    private final MoneyTransferRepository moneyTransferRepository;


    public OperationService(MoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
    }

    public Operation createOperation(TransferMoneyRequest transferMoneyRequest) {
        String operationId = IdCodeUtil.getOperationId();
        String verificationCode = IdCodeUtil.getVerificationCode();

        Operation operationData = new Operation();
        operationData.setOperationId(operationId).
                setCode(verificationCode).
                setTransferMoneyRequest(transferMoneyRequest);

        moneyTransferRepository.addOperation(operationData);
        log.info(LoggerUtil.createOperationLog(operationData));
        return operationData;
    }

    public Response confirmOperationRequest(ConfirmOperationRequest confirmOperationRequest) {
        String operationId = confirmOperationRequest.getOperationId();
        Operation operation = moneyTransferRepository.getOperation(operationId);

        if (operation == null)
            return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Not found operation data", 0);

        if (!(confirmOperationRequest.getCode().equals(operation.getCode())))
            return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Error input data", 0);

        return ResponseUtil.getResponse(HttpStatus.OK, "Success confirmation", 0).
                setOperationId(operationId);
    }

    public void removeOperation(String operationId) {
        moneyTransferRepository.removeOperation(operationId);
    }

    public Operation getOperationData(String operationId) {
        return moneyTransferRepository.getOperation(operationId);
    }
}
