package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class PetId implements Serializable {

    private String idUser;
    private String namePet;

    // 기본 생성자
    public PetId() {}

    // 생성자
    public PetId(String idUser, String namePet) {
        this.idUser = idUser;
        this.namePet = namePet;
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

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    // equals와 hashCode 구현 (필수)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetId petId = (PetId) o;
        return Objects.equals(idUser, petId.idUser) &&
                Objects.equals(namePet, petId.namePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, namePet);
    }
}
