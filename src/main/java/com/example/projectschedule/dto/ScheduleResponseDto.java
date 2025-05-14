package com.example.projectschedule.dto;

import com.example.projectschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String todo;
    private String author;
    private String isrt_dt;
    private String updt_dt;

    public ScheduleResponseDto(Schedule schedule) {

        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.isrt_dt = schedule.getIsrt_dt();
        this.updt_dt = schedule.getUpdt_dt();

    }

}
