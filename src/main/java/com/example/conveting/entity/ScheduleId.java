package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleId implements Serializable {

    private String idUser;
    private Integer idSchedule;

    // 기본 생성자
    public ScheduleId() {}

    // 매개변수 생성자
    public ScheduleId(String idUser, Integer idSchedule) {
        this.idUser = idUser;
        this.idSchedule = idSchedule;
    }

    // Getter와 Setter
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    // equals와 hashCode 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleId that = (ScheduleId) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idSchedule, that.idSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idSchedule);
    }
}