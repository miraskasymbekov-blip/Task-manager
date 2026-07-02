package com.miras.taskmanager.controller; // твой путь к пакету

import com.miras.taskmanager.dto.TaskRequestDto;
import com.miras.taskmanager.dto.TaskResponseDto;
import com.miras.taskmanager.entity.Task;
import com.miras.taskmanager.entity.TaskStatus;
import com.miras.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Реальный продакшн: при успешном создании возвращаем статус 201 Created
    public TaskResponseDto createTask(@RequestBody TaskRequestDto requestDto) {
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());

        Task createdTask = taskService.createTask(task);
        return TaskResponseDto.fromEntity(createdTask);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(TaskResponseDto::fromEntity) // Переводим каждую сущность из списка в DTO
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return TaskResponseDto.fromEntity(task);
    }

    @PatchMapping("/{id}/status")
    public TaskResponseDto updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task updatedTask = taskService.updateTaskStatus(id, status);
        return TaskResponseDto.fromEntity(updatedTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // При успешном удалении стандарт REST требует возвращать 204 No Content (без тела ответа)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}