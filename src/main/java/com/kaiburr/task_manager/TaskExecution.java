package com.kaiburr.task_manager;

import java.util.Date;

import lombok.Data;

@Data
public class TaskExecution {
    private Date startTime; // [cite: 40]
    private Date endTime; // [cite: 40]
    private String output; // [cite: 41]
}