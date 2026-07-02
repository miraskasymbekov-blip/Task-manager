package com.miras.taskmanager.dto;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
}