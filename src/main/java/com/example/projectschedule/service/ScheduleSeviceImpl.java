package com.example.projectschedule.service;

import com.example.projectschedule.dto.ScheduleRequestDto;
import com.example.projectschedule.dto.ScheduleResponseDto;
import com.example.projectschedule.entity.Schedule;
import com.example.projectschedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class ScheduleSeviceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleSeviceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getPassword(), dto.getDatetime(), dto.getAuthor(), dto.getTodo());

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public Map<Long, Schedule> findAllSchedules() {

        Map<Long, Schedule> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id));
    }

    @Override
    public ScheduleResponseDto updateScheduleById(Long id, String password, String datetime, String author, String todo) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (!password.equals(schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is different");
        }

        if (author == null || todo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The author and todo are required values.");
        }

        schedule.update(datetime, author, todo);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        scheduleRepository.deleteSchedule(id);

    }


}
