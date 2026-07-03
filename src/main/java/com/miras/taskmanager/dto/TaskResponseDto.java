package com.miras.taskmanager.dto; // твой путь к пакету

import com.miras.taskmanager.entity.Task;
import com.miras.taskmanager.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor // Нужен для ручного конструктора в методе fromEntity
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private UserResponseDto user;

    // Крутой инженерный трюк: статический фабричный метод для конвертации из Entity в DTO
    public static TaskResponseDto fromEntity(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getDeadline(),
                task.getUser() != null ? UserResponseDto.fromEntity(task.getUser()) : null

        );
    }
}