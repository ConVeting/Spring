package com.example.conveting.controller;

import com.example.conveting.entity.ScheduleEntity;
import com.example.conveting.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping("/main/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // 일정 조회 (일주일 단위)
    @GetMapping
    public List<ScheduleEntity> getWeeklySchedule() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
        return scheduleService.getWeeklySchedule(startOfWeek, endOfWeek);
    }

    // 일정 등록
    @PostMapping
    public ScheduleEntity createSchedule(@RequestBody ScheduleEntity scheduleEntity) {
        return scheduleService.createSchedule(scheduleEntity);
    }

    // 특정 날짜의 일정 조회
    @GetMapping("/{date}")
    public List<ScheduleEntity> getScheduleByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return scheduleService.getScheduleByDate(localDate);
    }
}
