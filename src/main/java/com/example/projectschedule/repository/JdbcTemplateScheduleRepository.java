package com.example.projectschedule.repository;

import com.example.projectschedule.dto.ScheduleResponseDto;
import com.example.projectschedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(date);

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("author", schedule.getAuthor());
        parameters.put("isrt_dt", now);
        parameters.put("updt_dt", now);
        parameters.put("password", schedule.getPassword());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(), schedule.getAuthor(), now, now);
    }

    @Override
    public Map<Long, Schedule> findAllSchedules() {

        List<Schedule> result = jdbcTemplate.query("select * from schedule order by updt_dt desc", scheduleRowMapper());
        Map<Long, Schedule> scheduleMap = new HashMap<>();
        for (Schedule schedule : result) {
            scheduleMap.put(schedule.getId(), schedule);
        }

        return scheduleMap;
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {

        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    @Override
    public int updateSchedule(Long id, String todo, String author) {

        return jdbcTemplate.update("update schedule set todo = ?, author = ?, updt_dt = now() where id = ?", todo, author, id);
    }

    @Override
    public int deleteSchedule(Long id) {

        return jdbcTemplate.update("delete from schedule where id = ?", id);

    }

    private RowMapper<Schedule> scheduleRowMapper() {

        return (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("todo"),
                rs.getString("author"),
                rs.getString("isrt_dt"),
                rs.getString("updt_dt")
        );
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {

        return (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("todo"),
                rs.getString("author"),
                rs.getString("isrt_dt"),
                rs.getString("updt_dt")
        );
    }
}
