package com.example.conveting.repository;

import com.example.conveting.entity.PetAllergyEntity;
import com.example.conveting.entity.PetAllergyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PetAllergyRepository extends JpaRepository<PetAllergyEntity, PetAllergyId> {
    @Transactional
    void deleteByPetId(String idUser, String name_pet);
}