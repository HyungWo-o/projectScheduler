package com.example.projectschedule.service;

import com.example.projectschedule.dto.ScheduleRequestDto;
import com.example.projectschedule.dto.ScheduleResponseDto;
import com.example.projectschedule.entity.Schedule;
import com.example.projectschedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        Schedule schedule = new Schedule(dto.getTodo(), dto.getAuthor(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public Map<Long, Schedule> findAllSchedules() {

        Map<Long, Schedule> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateScheduleById(Long id, String todo, String author, String password) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (!password.equals(schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is different");
        }

        if (todo == null || author == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The author and todo are required values.");
        }

        scheduleRepository.updateSchedule(id, todo, author);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (!password.equals(schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is different");
        }

        scheduleRepository.deleteSchedule(id);

    }


}
