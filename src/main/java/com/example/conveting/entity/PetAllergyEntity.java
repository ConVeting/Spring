package com.example.conveting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_allergy")
@IdClass(PetAllergyId.class)
public class PetAllergyEntity {

    @Id
    private String idUser;

    @Id
    private String namePet;

    @Id
    private String name_allergy;

    public PetAllergyEntity() {
    }

    public PetAllergyEntity(String idUser, String namePet, String name_allergy) {
        this.idUser = idUser;
        this.namePet = namePet;
        this.name_allergy = name_allergy;
    }

    // Getters and Setters
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setName_pet(String namePt) {
        this.namePet = namePet;
    }

    public String getName_allergy() {
        return name_allergy;
    }

    public void setName_allergy(String name_allergy) {
        this.name_allergy = name_allergy;
    }
}
