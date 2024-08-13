package com.example.conveting.service;

import com.example.conveting.entity.PetEntity;
import com.example.conveting.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<PetEntity> getAllPets() {
        return petRepository.findAll();
    }

    public PetEntity addPet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity updatePet(String name_pet, PetEntity petDetails) {
        PetEntity petEntity = petRepository.findByName(name_pet);

        if (petEntity != null) {
            petEntity.setName_pet(petDetails.getName_pet());
            petEntity.setBirth(petDetails.getBirth());
            petEntity.setWeight(petDetails.getWeight());
            petEntity.setSpecies(petDetails.getSpecies());
            petEntity.setSex(petDetails.isSex());
            petEntity.setNeuter(petDetails.isNeuter());
            return petRepository.save(petEntity);
        } else {
            throw new RuntimeException("Pet not found with name: " + name_pet);
        }
    }

    public void deletePet(String name_pet) {
        petRepository.deleteByName(name_pet);
    }
}
