package ru.netology.fvd.moneytransferservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netology.fvd.moneytransferservice.model.Response;
import ru.netology.fvd.moneytransferservice.model.Verification;
import ru.netology.fvd.moneytransferservice.model.Operation;
import ru.netology.fvd.moneytransferservice.model.Transaction;
import ru.netology.fvd.moneytransferservice.repository.MoneyTransferRepository;

//бизнес логика
@Service
public class MoneyTransferService {
    private final MoneyTransferRepository moneyTransferRepository;

    @Autowired
    public MoneyTransferService(MoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
    }

    public Response transfer(Transaction transaction) {
        //где будет валидироваться карта и перевод
        // и фиксироваться факт проведения транзакции(деньги пока не переводятся).
        if (moneyTransferRepository.getCards().containsKey(transaction.getCardFrom()) && moneyTransferRepository.getCards().containsKey(transaction.getCardTo()) && (transaction.getAmount().getValue() <= moneyTransferRepository.getCards().get(transaction.getCardFrom()).getAmount().getValue())) {
            new Operation(moneyTransferRepository.saveTransaction(transaction));

        }
        return null;
    }

    public Operation confirmOperation(Verification verification) {
        return moneyTransferRepository.confirmOperation(verification);
    }
}
