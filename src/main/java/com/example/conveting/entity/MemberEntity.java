package com.example.conveting.entity;

import com.example.conveting.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users") // DB에 생성되는 이름
public class MemberEntity {

    @Id
    @Column(name = "id_user", length = 30, nullable = false)
    private String idUser;

    @Column(name = "name_user", length = 20, nullable = false)
    private String name_user;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleEntity> scheduleEntities;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetEntity> petEntities;
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIdUser(memberDTO.getIdUser());
        memberEntity.setName_user(memberDTO.getName_user());
        memberEntity.setPassword(memberDTO.getPassword());
        return memberEntity;
    }
}
