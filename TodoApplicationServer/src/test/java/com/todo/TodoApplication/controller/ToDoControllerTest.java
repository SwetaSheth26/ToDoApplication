package com.todo.TodoApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.TodoApplication.model.Todo;
import com.todo.TodoApplication.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(TodoController.class)
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllToDos() throws Exception {
        List<Todo> allTodos =new ArrayList<>();
            allTodos.add(new Todo(1,"checkin the concurrency code", false, new Date(2025,04,11), new Date(2025,04,11)));
            allTodos.add(new Todo(2,"Some Leet code", false, new Date(2025,04,11), new Date(2025,04,11)));
            allTodos.add(new Todo(3,"Create Docker images", false, new Date(2025,04,11), new Date(2025,04,11)));

        Mockito.when(todoService.getAllTodo()).thenReturn(allTodos);
        mockMvc.perform(MockMvcRequestBuilders.get("/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void postToDos() throws Exception {
        Todo todo=new Todo(1,"checkin the concurrency code", false, new Date(2025,04,11), new Date(2025,04,11));
        List<Todo> allTodos =new ArrayList<>();


        allTodos.add(new Todo(1,"checkin the concurrency code", false, new Date(2025,04,11), new Date(2025,04,11)));
        allTodos.add(new Todo(2,"Some Leet code", false, new Date(2025,04,11), new Date(2025,04,11)));
        allTodos.add(new Todo(3,"Create Docker images", false, new Date(2025,04,11), new Date(2025,04,11)));

        Mockito.when(todoService.addTodo(todo)).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
