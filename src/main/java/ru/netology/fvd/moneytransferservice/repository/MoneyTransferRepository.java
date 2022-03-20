package ru.netology.fvd.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.fvd.moneytransferservice.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MoneyTransferRepository {

    private final Map<String, Operation> transactions = new ConcurrentHashMap<String, Operation>();

    public MoneyTransferRepository() {
    }


    public Operation getOperation(String operationId) {
        return transactions.get(operationId);
    }

    public void addOperation(Operation operation) {
        transactions.put(operation.getOperationId(), operation);
    }

    public void removeOperation(String operationId) {
        transactions.remove(operationId);
    }

}
