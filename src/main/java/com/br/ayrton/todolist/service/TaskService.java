package com.br.ayrton.todolist.service;

import com.br.ayrton.todolist.model.Task;
import com.br.ayrton.todolist.repositoy.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask (Task task){
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> findTaskById(Long id){
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteTaskById(Long id){
        return taskRepository.findById(id)
                .map(taskToDelete ->{
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateTaskById(Task task, Long id){
        return taskRepository.findById(id)
                .map(taskToUpdate ->{
                 taskToUpdate.setTitle(task.getTitle());
                 taskToUpdate.setDescription(taskToUpdate.getDescription());
                 taskToUpdate.setDeadLine(taskToUpdate.getDeadLine());
                 Task updated = taskRepository.save(task);
                 return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
