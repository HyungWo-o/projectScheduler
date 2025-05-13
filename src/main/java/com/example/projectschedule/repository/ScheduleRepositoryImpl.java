package com.example.projectschedule.repository;

import com.example.projectschedule.dto.ScheduleRequestDto;
import com.example.projectschedule.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    @Override
    public Schedule saveSchedule(Schedule schedule) {

        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        schedule.setId(scheduleId);

        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    @Override
    public Map<Long, Schedule> findAllSchedules() {

        return scheduleList;
    }

    @Override
    public Schedule findScheduleById(Long id) {

        Schedule schedule = scheduleList.get(id);

        return schedule;
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleList.remove(id);
    }

}
