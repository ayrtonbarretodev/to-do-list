package com.br.ayrton.todolist.repositoy;

import com.br.ayrton.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select t from Task t where t.status= 'CONCLUIDA' ")
    List<Task> findByStatusTaskCompleted();
    @Query("select t from Task t where t.status= 'ANDAMENTO' ")
    List<Task> findByStatusTaskInProgress();
    @Query("select t from Task t where t.status= 'NAOINICIADA' ")
    List<Task> findByStatusTaskNotStarted();
}
