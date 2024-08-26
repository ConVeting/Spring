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
                   savedPet.getIdUser(), savedPet.getNamePet(), allergy);
            petAllergyRepository.save(petAllergyEntity);
        }
        return savedPet;
    }

    public PetEntity updatePet(String namePet, PetEntity petDetails, List<String> allergies) {
        PetEntity petEntity = petRepository.findByNamePet(namePet);

        if (petEntity != null) {
            petEntity.setNamePet(petDetails.getNamePet());
            petEntity.setBirth(petDetails.getBirth());
            petEntity.setWeight(petDetails.getWeight());
            petEntity.setSpecies(petDetails.getSpecies());
            petEntity.setSex(petDetails.isSex());
            petEntity.setNeuter(petDetails.isNeuter());
            PetEntity updatedPet = petRepository.save(petEntity);

            petAllergyRepository.deleteByIdUserAndNamePet(updatedPet.getIdUser(), updatedPet.getNamePet());
            for (String allergy : allergies) {
                PetAllergyEntity petAllergyEntity = new PetAllergyEntity(
                     updatedPet.getIdUser(), updatedPet.getNamePet(), allergy);
                petAllergyRepository.save(petAllergyEntity);
            }

            return updatedPet;
        } else {
            throw new RuntimeException("Pet not found with name: " + namePet);
        }
    }

    public void deletePet(String namePet) {

        PetEntity petEntity = petRepository.findByNamePet(namePet);


        if (petEntity != null) {
            // Pet ID를 사용하여 알러지 정보를 먼저 삭제합니다.
            petAllergyRepository.deleteByIdUserAndNamePet(petEntity.getIdUser(), petEntity.getNamePet());
            // 그런 다음 펫 엔터티를 삭제합니다.
            petRepository.delete(petEntity);
        } else {
            throw new RuntimeException("Pet not found with name: " + namePet);
        }
    }
}

