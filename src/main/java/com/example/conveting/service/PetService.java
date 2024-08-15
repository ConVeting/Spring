package com.example.conveting.service;

import com.example.conveting.entity.PetAllergyEntity;
import com.example.conveting.entity.PetAllergyId;
import com.example.conveting.entity.PetEntity;
import com.example.conveting.repository.PetAllergyRepository;
import com.example.conveting.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetAllergyRepository petAllergyRepository;

    public List<PetEntity> getAllPets() {
        return petRepository.findAll();
    }

    public PetEntity addPet(PetEntity petEntity, List<String> allergies) {
        PetEntity savedPet = petRepository.save(petEntity);
        for (String allergy : allergies) {
            PetAllergyEntity petAllergyEntity = new PetAllergyEntity(
                    new PetAllergyId(savedPet.getIdUser(), savedPet.getName_pet(), allergy)
            );
            petAllergyRepository.save(petAllergyEntity);
        }
        return savedPet;
    }

    public PetEntity updatePet(String name_pet, PetEntity petDetails, List<String> allergies) {
        PetEntity petEntity = petRepository.findByName(name_pet);

        if (petEntity != null) {
            petEntity.setName_pet(petDetails.getName_pet());
            petEntity.setBirth(petDetails.getBirth());
            petEntity.setWeight(petDetails.getWeight());
            petEntity.setSpecies(petDetails.getSpecies());
            petEntity.setSex(petDetails.isSex());
            petEntity.setNeuter(petDetails.isNeuter());
            PetEntity updatedPet = petRepository.save(petEntity);

            petAllergyRepository.deleteByPetId(updatedPet.getIdUser(), updatedPet.getName_pet());
            for (String allergy : allergies) {
                PetAllergyEntity petAllergyEntity = new PetAllergyEntity(
                        new PetAllergyId(updatedPet.getIdUser(), updatedPet.getName_pet(), allergy)
                );
                petAllergyRepository.save(petAllergyEntity);
            }

            return updatedPet;
        } else {
            throw new RuntimeException("Pet not found with name: " + name_pet);
        }
    }

    public void deletePet(String name_pet) {

        Optional<PetEntity> optionalPetEntity = petRepository.findByNamePet(name_pet);


        if (optionalPetEntity.isPresent()) {
            PetEntity existingPet = optionalPetEntity.get();
            petAllergyRepository.deleteByPetId(existingPet.getIdUser(), existingPet.getName_pet());
            petRepository.delete(existingPet);
        } else {
            throw new RuntimeException("Pet not found with name: " + name_pet);
        }
    }
}

