package ru.netology.fvd.moneytransferservice.exceptions;

public class ErrorTransfer extends RuntimeException{
    public ErrorTransfer() {
        super("Transfer Error");
    }
}
