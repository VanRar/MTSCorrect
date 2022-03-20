package ru.netology.fvd.moneytransferservice.exceptions;

public class ErrorInputData  extends RuntimeException{
    public ErrorInputData(){
        super("Input data error");
    }
    public ErrorInputData(String s){
        super(s);
    }
}
