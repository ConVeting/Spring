package com.example.conveting.controller;

import com.example.conveting.dto.PetDTO;
import com.example.conveting.entity.MemberEntity;
import com.example.conveting.entity.PetEntity;
import com.example.conveting.service.MemberService;
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
    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<PetEntity> getAllPets() {
        return petService.getAllPets();
    }

    @PostMapping
    public PetEntity addPet(@RequestBody PetDTO petDTO) {
        // MemberEntity를 가져옵니다.
        MemberEntity memberEntity = memberService.findByIdUser(petDTO.getIdUser());
        if (memberEntity == null) {
            throw new IllegalArgumentException("Invalid user ID: " + petDTO.getIdUser());
        }

        // PetEntity를 생성하고 저장합니다.
        PetEntity petEntity = PetEntity.toSaveEntity(petDTO, memberEntity);
        return petService.addPet(petEntity, petDTO.getAllergies());
    }


    @PutMapping("/{petname}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable("petname") String namePet, @RequestBody PetDTO petDTO) {
        // MemberEntity를 가져옵니다.
        MemberEntity memberEntity = memberService.findByIdUser(petDTO.getIdUser());
        if (memberEntity == null) {
            throw new IllegalArgumentException("Invalid user ID: " + petDTO.getIdUser());
        }

        // PetEntity를 생성하고 업데이트합니다.
        PetEntity petEntity = PetEntity.toSaveEntity(petDTO, memberEntity);
        PetEntity updatedPet = petService.updatePet(namePet, petEntity, petDTO.getAllergies());
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{petname}")
    public ResponseEntity<String> deletePet(@PathVariable("petname") String namePet) {
        petService.deletePet(namePet);
        return ResponseEntity.ok("Pet deleted successfully");
    }
}
