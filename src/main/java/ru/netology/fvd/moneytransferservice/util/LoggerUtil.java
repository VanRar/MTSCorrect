package ru.netology.fvd.moneytransferservice.util;


import ru.netology.fvd.moneytransferservice.model.Operation;

public class LoggerUtil {
    private static final String STATUS_OPERATION_CREATED = "STATUS: OPERATION CREATED";
    private static final String STATUS_MONEY_TRANSFER_SUCCESS = "STATUS: MONEY TRANSFER SUCCESS";

    public static String createOperationLog(Operation operationData) {
        return STATUS_OPERATION_CREATED + operationData.getLog();
    }

    public static String transferMoneyLog(Operation operationData) {
        return STATUS_MONEY_TRANSFER_SUCCESS + operationData.getLog();
    }
}
