package com.marcRG.javaCommunity.repository;

import com.marcRG.javaCommunity.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    Optional<Task> findTaskByDescription(String description);
}
