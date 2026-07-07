package com.miras.taskmanager.dto;

public interface TaskStatsProjection {
    long getNewCount();
    long getInProgressCount();
    long getDoneCount();
    long getOverdueCount();
}