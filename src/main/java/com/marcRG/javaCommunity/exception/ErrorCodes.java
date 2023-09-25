package com.marcRG.javaCommunity.exception;

public enum ErrorCodes {

    Task_Not_Found(1001),
    Task_Not_Valid(1002),
    User_Not_Found(1003),
    User_Not_valid(1004);

    ErrorCodes(int code){
        this.errorCode=code;
    }
    private int errorCode;

    public int getErrorCode(){
        return errorCode;
    }
}
