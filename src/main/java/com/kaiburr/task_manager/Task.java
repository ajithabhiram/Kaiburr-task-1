// src/main/java/com/kaiburr/taskmanager/Task.java
package com.kaiburr.task_manager;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "tasks") // Maps this class to the "tasks" collection in MongoDB
public class Task {
    @Id
    private String id; // [cite: 34]
    private String name; // [cite: 35]
    private String owner; // [cite: 36]
    private String command; // [cite: 37]
    private List<TaskExecution> taskExecutions; // [cite: 38]
}