package com.miras.taskmanager.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Название задачи не должно быть пустым")
    @Size(min = 3, max = 100, message = "Название должно быть от 3 до 100 символов")
    private String title;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;

    @NotNull(message = "ID пользователя обязателен для заполнения")
    private Long userId; // <-- Новое поле

    @FutureOrPresent
    private LocalDateTime deadline;
}