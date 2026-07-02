package com.miras.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Название задачи не должно быть пустым")
    @Size(min = 3, max = 100, message = "Название должно быть от 3 до 100 символов")
    private String title;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;
}