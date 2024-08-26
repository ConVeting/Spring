package com.example.conveting.repository;

import com.example.conveting.entity.PetEntity;
import com.example.conveting.entity.PetId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<PetEntity, PetId> {
    PetEntity findByNamePet(String namePet);
    void deleteByNamePet(String namePet);
}
