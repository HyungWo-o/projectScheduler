package com.example.projectschedule.dto;

import com.example.projectschedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String datetime;
    private String author;
    private String todo;

    public ScheduleResponseDto(Schedule schedule) {

        this.id = schedule.getId();
        this.datetime = schedule.getDatetime();
        this.author = schedule.getAuthor();
        this.todo = schedule.getTodo();

    }

}
