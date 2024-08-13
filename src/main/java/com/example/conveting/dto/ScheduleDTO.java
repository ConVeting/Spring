package com.example.conveting.dto;

import com.example.conveting.entity.ScheduleEntity;
import com.example.conveting.repository.ScheduleRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleDTO {
    private String idUser;
    private Long idSchedule;
    private Date date;
    private String contentSchedule;

    public static ScheduleDTO toScheduleDTO(ScheduleEntity scheduleEntity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setIdUser(scheduleEntity.getIdUser());
        scheduleDTO.setIdSchedule(scheduleEntity.getIdSchedule());
        scheduleDTO.setDate(scheduleEntity.getDate());
        scheduleDTO.setContentSchedule(scheduleEntity.getContentSchedule());
        return scheduleDTO;
    }
}
