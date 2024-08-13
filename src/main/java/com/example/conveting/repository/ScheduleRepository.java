package com.example.conveting.repository;

import com.example.conveting.entity.ScheduleEntity;
import com.example.conveting.entity.ScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, ScheduleId> {
    List<ScheduleEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    List<ScheduleEntity> findAllByDate(LocalDate date);
}
