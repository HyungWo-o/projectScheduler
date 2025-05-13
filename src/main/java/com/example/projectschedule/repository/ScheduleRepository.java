package com.example.projectschedule.repository;

import com.example.projectschedule.entity.Schedule;

import java.util.Map;

public interface ScheduleRepository {

    Schedule saveSchedule(Schedule schedule);
    Map<Long, Schedule> findAllSchedules();
    Schedule findScheduleById(Long id);
    void deleteSchedule(Long id);

}
