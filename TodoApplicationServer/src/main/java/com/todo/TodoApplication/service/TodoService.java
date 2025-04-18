package com.todo.TodoApplication.service;

import com.todo.TodoApplication.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class TodoService {
    List<Todo> allTodos =new ArrayList<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    TodoService(){
        allTodos.add(new Todo(1,"checkin the concurrency code", false, new Date(2025,04,11), new Date(2025,04,11)));
        allTodos.add(new Todo(2,"Some Leet code", false, new Date(2025,04,11), new Date(2025,04,11)));
        allTodos.add(new Todo(3,"Create Docker images", false, new Date(2025,04,11), new Date(2025,04,11)));
    }
    public List<Todo> getAllTodo(){
        return allTodos;
    }

    public Todo addTodo(Todo todo){
        lock.writeLock().lock();
        allTodos.add(todo);
        lock.writeLock().unlock();
        return todo;
    }
}
