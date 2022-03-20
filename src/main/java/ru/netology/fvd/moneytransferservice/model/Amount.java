package ru.netology.fvd.moneytransferservice.model;

import java.util.Objects;

public class Amount {
    int value;//значения целые, два знака после запятой включительно (11.11 записывается как 1111)
    String currency;

    public Amount(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value && currency.equals(amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}