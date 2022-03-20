package ru.netology.fvd.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.fvd.moneytransferservice.exceptions.ErrorInputData;
import ru.netology.fvd.moneytransferservice.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//работа с данными
@Repository
public class MoneyTransferRepository {
    //храним наши транзакции в мапе
    private final Map<String, Transaction> transactions = new ConcurrentHashMap<String, Transaction>();
    private Map<String, Card> cards = new ConcurrentHashMap<String, Card>();
    //сохраняем логи
    private static final Logger logger = LogManager.getLogger(MoneyTransferRepository.class);

    public MoneyTransferRepository() {
        Card card1 = new Card(" 1111 1111 1111 1111", "11/23", "111", new Amount(100_000, "RUR"));
        Card card2 = new Card(" 1111 1111 1111 2222", "12/23", "111", new Amount(200_000, "RUR"));

        this.cards.put(card1.getCardNumber(), card1);
        this.cards.put(card2.getCardNumber(), card2);

    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public String saveTransaction(Transaction transaction) {
        long unixTime = System.currentTimeMillis() / 1000L;
        String time = Long.toString(unixTime);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(time)
                .append("D")
                .append(transaction.hashCode());//создаем уникальный номер
        transactions.put(stringBuilder.toString(), transaction);//сохраняем транзакцию
        logger.info("Запрос на перевод " + stringBuilder.toString() + " " + transaction);
        return stringBuilder.toString();//тут сохраняем транзакцию и возвращаем уникальный номер
    }

    public Operation confirmOperation(Verification verification) {
        logger.info("Перевод подтвержден " + verification.toString());
        return new Operation(verification.getOperationId());
    }


}
