package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class PetAllergyId implements Serializable {

    private String idUser;
    private String namePet;
    private String name_allergy;

    public PetAllergyId() {
    }

    public PetAllergyId(String idUser, String namePet, String name_allergy) {
        this.idUser = idUser;
        this.namePet = namePet;
        this.name_allergy = name_allergy;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getName_allergy() {
        return name_allergy;
    }

    public void setName_allergy(String name_allergy) {
        this.name_allergy = name_allergy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAllergyId that = (PetAllergyId) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(namePet, that.namePet) &&
                Objects.equals(name_allergy, that.name_allergy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, namePet, name_allergy);
    }
}
