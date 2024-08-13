package com.example.conveting.dto;

import com.example.conveting.entity.PetEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PetDTO {
    private String idUser;
    private String name_pet;
    private Date birth;
    private double weight;
    private String species;
    private boolean sex;
    private boolean neuter;

    public static PetDTO toPetDTO(PetEntity petEntity, String idUser) {
        PetDTO petDTO = new PetDTO();
        petDTO.setIdUser(petEntity.getIdUser());
        petDTO.setName_pet(petEntity.getName_pet());
        petDTO.setBirth(petEntity.getBirth());
        petDTO.setWeight(petEntity.getWeight());
        petDTO.setSpecies(petEntity.getSpecies());
        petDTO.setSex(petEntity.isSex());
        petDTO.setNeuter(petEntity.isNeuter());
        return petDTO;
    }
}
