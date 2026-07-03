package com.miras.taskmanager.repository;

import com.miras.taskmanager.entity.Task;
import com.miras.taskmanager.entity.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId, Sort sort);
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status, Sort sort);

}