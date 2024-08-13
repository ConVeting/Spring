package com.example.conveting.repository;

import com.example.conveting.entity.PetEntity;
import com.example.conveting.entity.PetId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, PetId> {
    PetEntity findByName(String name_pet);
    void deleteByName(String name_pet);
}
