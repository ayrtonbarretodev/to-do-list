package com.br.ayrton.todolist.controller;

import com.br.ayrton.todolist.model.Task;
import com.br.ayrton.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask (@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }
}
