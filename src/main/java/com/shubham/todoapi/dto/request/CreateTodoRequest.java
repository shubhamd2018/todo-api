package com.shubham.todoapi.dto.request;

public class CreateTodoRequest {
    private String title;

    public CreateTodoRequest(){}

    public CreateTodoRequest(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
