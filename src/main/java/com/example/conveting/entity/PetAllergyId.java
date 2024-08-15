package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class PetAllergyId implements Serializable {

    private String idUser;
    private String name_pet;
    private String name_allergy;

    public PetAllergyId() {
    }

    public PetAllergyId(String idUser, String name_pet, String name_allergy) {
        this.idUser = idUser;
        this.name_pet = name_pet;
        this.name_allergy = name_allergy;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName_pet() {
        return name_pet;
    }

    public void setName_pet(String name_pet) {
        this.name_pet = name_pet;
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
                Objects.equals(name_pet, that.name_pet) &&
                Objects.equals(name_allergy, that.name_allergy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name_pet, name_allergy);
    }
}
