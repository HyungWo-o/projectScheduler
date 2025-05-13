package com.example.projectschedule.entity;

import com.example.projectschedule.dto.ScheduleRequestDto;
import com.example.projectschedule.dto.ScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private Long id;
    private String password;
    private String datetime;
    private String author;
    private String todo;

    public Schedule(String password, String datetime, String author, String todo) {
        this.password = password;
        this.datetime = datetime;
        this.author = author;
        this.todo = todo;
    }

    public void update(String datetime, String author, String todo) {
        this.datetime = datetime;
        this.author = author;
        this.todo = todo;
    }

}
