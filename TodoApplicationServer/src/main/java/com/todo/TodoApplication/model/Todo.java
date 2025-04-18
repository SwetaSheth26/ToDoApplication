package com.todo.TodoApplication.model;

import java.util.Date;

public record Todo(String username,int id, String description, boolean isDone, Date dueBy, Date createAt) {

    public Todo(int id, String description, boolean isDone, Date dueBy, Date createAt) {
        this(null, id, description, isDone, dueBy, createAt);
    }
    /*implement builder pattern

   private Todo(Builder b){

    }

    public static class Builder{

    }*/
}
