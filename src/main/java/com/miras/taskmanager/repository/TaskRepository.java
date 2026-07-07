package com.miras.taskmanager.repository;

import com.miras.taskmanager.dto.TaskStatsProjection;
import com.miras.taskmanager.entity.Task;
import com.miras.taskmanager.entity.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId, Sort sort);
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status, Sort sort);

    @Query(value = """
        SELECT 
            COUNT(*) FILTER (WHERE status = 'NEW') as newCount, 
            COUNT(*) FILTER (WHERE status = 'IN_PROGRESS') as inProgressCount, 
            COUNT(*) FILTER (WHERE status = 'DONE') as doneCount, 
            COUNT(*) FILTER (WHERE status != 'DONE' AND deadline < :now) as overdueCount 
        FROM tasks 
        WHERE user_id = :userId
        """, nativeQuery = true)
    TaskStatsProjection getTaskStats(@Param("userId") Long userId, @Param("now") LocalDateTime now);

}