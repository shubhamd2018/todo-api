package com.shubham.todoapi.dto.response;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(){}

    public ErrorResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}
