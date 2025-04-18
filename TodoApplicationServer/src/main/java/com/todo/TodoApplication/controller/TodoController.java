package com.todo.TodoApplication.controller;

import com.todo.TodoApplication.model.Todo;
import com.todo.TodoApplication.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService service;

    @GetMapping
    public List<Todo> getAllTodo(HttpServletRequest request){
        /*System.out.println(request.getUserPrincipal());
        System.out.println(request.getUserPrincipal().getName());*/
        return service.getAllTodo();
    }

    @PostMapping
    public ResponseEntity<Todo> getAllTodo(@RequestBody Todo todo){
        /*System.out.println(request.getUserPrincipal());
        System.out.println(request.getUserPrincipal().getName());*/
        return new ResponseEntity<>(service.addTodo(todo),HttpStatus.CREATED);
    }
}
