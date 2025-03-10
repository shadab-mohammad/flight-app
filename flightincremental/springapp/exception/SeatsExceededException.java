package com.examly.springapp.exception;

public class SeatsExceededException extends Exception{
    public SeatsExceededException(){}
    public SeatsExceededException(String message){
        super(message);
    }
}
