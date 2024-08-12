package com.example.conveting.dto;

import com.example.conveting.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private String idUser;
    private String name_user;
    private String password;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setIdUser(memberEntity.getIdUser());
        memberDTO.setName_user(memberEntity.getName_user());
        memberDTO.setPassword(memberEntity.getPassword());
        return memberDTO;
    }
}
