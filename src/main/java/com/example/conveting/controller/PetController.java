package com.example.conveting.controller;

import com.example.conveting.entity.PetEntity;
import com.example.conveting.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mypet")

public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping
    public List<PetEntity> getAllPets() {
        return petService.getAllPets();
    }

    @PostMapping
    public PetEntity addPet(@RequestBody PetEntity petEntity) {
        return petService.addPet(petEntity);
    }

    @PutMapping("/{petname}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable String name_pet, @RequestBody PetEntity petDetails) {
        PetEntity updatedPet = petService.updatePet(name_pet, petDetails);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{petname}")
    public ResponseEntity<String> deletePet(@PathVariable String name_pet) {
        petService.deletePet(name_pet);
        return ResponseEntity.ok("Pet deleted successfully");
    }
}
