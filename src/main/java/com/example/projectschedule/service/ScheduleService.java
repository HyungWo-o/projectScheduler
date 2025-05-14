package com.example.projectschedule.service;

import com.example.projectschedule.dto.ScheduleRequestDto;
import com.example.projectschedule.dto.ScheduleResponseDto;
import com.example.projectschedule.entity.Schedule;

import java.util.Map;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    Map<Long, Schedule> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateScheduleById(Long id, String todo, String author, String password);

    void deleteSchedule(Long id, String password);

}
