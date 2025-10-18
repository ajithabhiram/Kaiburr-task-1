// src/main/java/com/kaiburr/taskmanager/TaskRepository.java
package com.kaiburr.task_manager;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    
    List<Task> findByNameContainingIgnoreCase(String name);
}