package com.example.projectschedule.repository;

import com.example.projectschedule.dto.ScheduleResponseDto;
import com.example.projectschedule.entity.Schedule;

import java.util.Map;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    Map<Long, Schedule> findAllSchedules();
    Optional<Schedule> findScheduleById(Long id);
    Schedule findScheduleByIdOrElseThrow(Long id);
    int updateSchedule(Long id, String todo, String author);
    int deleteSchedule(Long id);

}
