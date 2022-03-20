package ru.netology.fvd.moneytransferservice.model;

import java.util.Objects;

public class Transaction {
    private final Card cardFrom;
    private Card cardTo;
    private Amount amount;
    private Operation operation;

    //создаем из запроса
    public Transaction(String cardFromNumber,
                       String cardFromValidTill,
                       String cardFromCvv,
                       String cardToNumber,
                       Amount amount) {
        this.cardFrom = new Card(cardFromNumber,cardFromValidTill,cardFromCvv,amount);//по счету криво, но как есть, по другому на данном этапе нарна никак
        this.cardTo= new Card(cardToNumber);
        this.amount = amount;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public Amount getAmount() {
        return amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(cardFrom, that.cardFrom) && Objects.equals(cardTo, that.cardTo) && Objects.equals(amount, that.amount) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFrom, cardTo, amount, operation);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "cardFrom=" + cardFrom +
                ", cardTo=" + cardTo +
                ", amount=" + amount +
                ", operation=" + operation +
                '}';
    }
}
//{"cardFromNumber": "1231323123132131",
//"cardToNumber": "5456465465465465",
//"cardFromCVV": "111",
//"cardFromValidTill": "06/23",
//"amount": {
//"currency": "RUR",
//"value": 12300}
//}