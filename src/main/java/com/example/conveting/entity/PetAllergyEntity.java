package com.example.conveting.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_allergy")
public class PetAllergyEntity {
    @EmbeddedId
    private PetAllergyId id;

    public PetAllergyEntity() {
    }

    public PetAllergyEntity(PetAllergyId id) {
        this.id = id;
    }

    public PetAllergyId getId() {
        return id;
    }

    public void setId(PetAllergyId id) {
        this.id = id;
    }
}
