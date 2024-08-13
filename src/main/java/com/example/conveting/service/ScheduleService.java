package com.example.conveting.service;

import com.example.conveting.entity.ScheduleEntity;
import com.example.conveting.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleEntity> getWeeklySchedule(LocalDate startDate, LocalDate endDate) {
        return scheduleRepository.findAllByDateBetween(startDate, endDate);
    }

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> getScheduleByDate(LocalDate date) {
        return scheduleRepository.findAllByDate(date);
    }
}
