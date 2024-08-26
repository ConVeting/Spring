package com.example.conveting.entity;

import com.example.conveting.dto.PetDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@IdClass(PetId.class)
@Setter
@Getter
@Table(name = "pet")
public class PetEntity {
    @Id
    @Column(length = 30)
    private String idUser;

    @Id
    @Column(name = "name_pet", length = 10)
    private String namePet;

    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(length = 30)
    private String species;
    @Column
    private boolean sex;
    @Column
    private boolean neuter;

    // Many-to-One 관계 설정 (Foreign Key)
    @ManyToOne
    @JoinColumn(name = "idUser", insertable = false, updatable = false)
    private MemberEntity memberEntity;

    public static PetEntity toSaveEntity(PetDTO petDTO, MemberEntity memberEntity) {
        PetEntity petEntity = new PetEntity();
        petEntity.setIdUser(petDTO.getIdUser());
        petEntity.setNamePet(petDTO.getNamePet());
        petEntity.setBirth(petDTO.getBirth());
        petEntity.setWeight(petDTO.getWeight());
        petEntity.setSex(petDTO.isSex());
        petEntity.setNeuter(petDTO.isNeuter());
        return petEntity;
    }
}
