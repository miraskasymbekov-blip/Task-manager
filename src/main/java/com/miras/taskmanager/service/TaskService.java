package com.miras.taskmanager.service; // твой путь к пакету

import com.miras.taskmanager.entity.Task;
import com.miras.taskmanager.entity.TaskStatus;
import com.miras.taskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // По умолчанию все методы только на чтение (оптимизация БД)
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional // Тут транзакция нужна, так как идет запись в БД
    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Название задачи не может быть пустым");
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Задача с id " + id + " не найдена"));
    }

    @Transactional
    public Task updateTaskStatus(Long id, TaskStatus newStatus) {
        Task task = getTaskById(id);
        task.setStatus(newStatus);
        return task;
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Невозможно удалить: задача с id " + id + " не найдена");
        }
        taskRepository.deleteById(id);
    }
}