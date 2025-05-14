package com.example.projectschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

//    @Setter
    private Long id;
    private String todo;
    private String author;
    private String isrt_dt;
    private String updt_dt;
    private String password;

    public Schedule(String todo, String author, String password) {
        this.todo = todo;
        this.author = author;
        this.password = password;
    }

    public Schedule(Long id, String todo, String author, String isrt_dt, String updt_dt) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.isrt_dt = isrt_dt;
        this.updt_dt = updt_dt;
    }

    public void update(String todo, String author, String updt_dt ) {
        this.todo = todo;
        this.author = author;
        this.updt_dt = updt_dt;
    }

}
