package com.miras.taskmanager.dto;

import com.miras.taskmanager.entity.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;

    public static UserResponseDto fromEntity(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}