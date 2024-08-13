package com.example.conveting.entity;

import com.example.conveting.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@IdClass(ScheduleId.class)
@Getter
@Setter
@Table(name = "Schedule")
public class ScheduleEntity {
    @Id
    @Column(name = "id_user", length = 30)
    private String idUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Long idSchedule;

    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "content_schedule", length = 255)
    private String contentSchedule;

    @ManyToOne
    @JoinColumn(name = "idUser", insertable = false, updatable = false)
    private MemberEntity memberEntity;  // Users 엔티티와의 연관관계

    public static ScheduleEntity toSaveEntity(ScheduleDTO scheduleDTO){
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setIdUser(scheduleDTO.getIdUser());
        scheduleEntity.setIdSchedule(scheduleDTO.getIdSchedule());
        scheduleEntity.setDate(scheduleDTO.getDate());
        scheduleEntity.setContentSchedule(scheduleDTO.getContentSchedule());
        return scheduleEntity;
    }
}
