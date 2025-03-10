package com.examly.springapp.exception;

public class DuplicateFlightException extends Exception{
    public DuplicateFlightException(){}
    public DuplicateFlightException(String message){
        super(message);
    }
}
