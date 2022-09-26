package com.br.ayrton.todolist.service;

import com.br.ayrton.todolist.model.Task;
import com.br.ayrton.todolist.repositoy.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask (Task task){
        return taskRepository.save(task);
    }
}
