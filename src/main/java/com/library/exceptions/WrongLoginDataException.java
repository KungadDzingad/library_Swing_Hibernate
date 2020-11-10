package com.library.exceptions;

public class WrongLoginDataException extends Exception{
    public WrongLoginDataException(){
        super("Wrong Login Data Try Again");
    }
}
