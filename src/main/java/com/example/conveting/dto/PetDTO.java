package com.example.conveting.dto;

import com.example.conveting.entity.PetEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PetDTO {
    private String idUser;
    private String namePet;
    private Date birth;
    private BigDecimal weight;
    private String species;
    private boolean sex;
    private boolean neuter;
    private List<String> allergies;

    public static PetDTO toPetDTO(PetEntity petEntity, String idUser) {
        PetDTO petDTO = new PetDTO();
        petDTO.setIdUser(petEntity.getIdUser());
        petDTO.setNamePet(petEntity.getNamePet());
        petDTO.setBirth(petEntity.getBirth());
        petDTO.setWeight(petEntity.getWeight());
        petDTO.setSpecies(petEntity.getSpecies());
        petDTO.setSex(petEntity.isSex());
        petDTO.setNeuter(petEntity.isNeuter());
        return petDTO;
    }
}
