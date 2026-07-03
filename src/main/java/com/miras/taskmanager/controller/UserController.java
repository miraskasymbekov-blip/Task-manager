package com.miras.taskmanager.controller;

import com.miras.taskmanager.dto.TaskResponseDto;
import com.miras.taskmanager.dto.UserRequestDto;
import com.miras.taskmanager.dto.UserResponseDto;
import com.miras.taskmanager.entity.User;
import com.miras.taskmanager.service.TaskService;
import com.miras.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());

        User createdUser = userService.createUser(user);
        return UserResponseDto.fromEntity(createdUser);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserResponseDto::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return UserResponseDto.fromEntity(user);
    }

    @GetMapping("/{userId}/tasks")
    public List<TaskResponseDto> getUserTasks(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId).stream()
                .map(TaskResponseDto::fromEntity)
                .toList();
    }
}