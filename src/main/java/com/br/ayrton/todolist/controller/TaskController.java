package com.br.ayrton.todolist.controller;

import com.br.ayrton.todolist.model.Task;
import com.br.ayrton.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private TaskService taskService;


    @Operation(summary = "Create Task",description = "Criando uma nova tarefa",responses = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar a tarefa, verifique as informações")
    })

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask (@RequestBody Task task){
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @Operation(summary = "List Task",description = "Listando todas as tarefas",responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listar as tarefas")
    })

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        log.info("Listando todas as tarefas cadastradas");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.listAllTasks());
    }

    @Operation(summary = "List Task Completed",description = "Listando todas as tarefas completadas",responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas completas listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listar as tarefas completadas")
    })

    @GetMapping("/tasks/completed")
    public ResponseEntity<List<Task>> getAllTasksCompleted(){
        log.info("Listando todas as tarefas conclídas");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.listAllTasksCompleted());
    }

    @Operation(summary = "List Task By Id",description = "Buscando uma tarefa por id", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrada nenhuma tarefa com esse id")
    })

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id){
        log.info("Buscando tarefa com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @Operation(summary = "Update Task",description = "Atualizando uma tarefa",responses = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível atualizar a tarefa - tarefa não encontrada")
    })

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
        log.info("Atualizando a tarefa com o id [{}] as novas informações são: [{}]",id,task);
        return taskService.updateTaskById(task,id);
    }

    @Operation(summary = "Delete Task",description = "Deletando uma tarefa", responses = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível excluir a tarefa - tarefa não encontrada")
    })

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
        log.info("Excluindo tarefas com o id [{}]", id);
        return taskService.deleteTaskById(id);
    }
}
