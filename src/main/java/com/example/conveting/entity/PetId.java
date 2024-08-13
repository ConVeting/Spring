package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class PetId implements Serializable {

    private String idUser;
    private String name_pet;

    // 기본 생성자
    public PetId() {}

    // 생성자
    public PetId(String idUser, String name_pet) {
        this.idUser = idUser;
        this.name_pet = name_pet;
    }

    // Getters and Setters
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName_pet() {
        return name_pet;
    }

    public void setNamePet(String name_pet) {
        this.name_pet = name_pet;
    }

    // equals와 hashCode 구현 (필수)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetId petId = (PetId) o;
        return Objects.equals(idUser, petId.idUser) &&
                Objects.equals(name_pet, petId.name_pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name_pet);
    }
}
