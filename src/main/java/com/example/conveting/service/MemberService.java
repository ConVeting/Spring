package com.example.conveting.service;

import com.example.conveting.dto.MemberDTO;
import com.example.conveting.entity.MemberEntity;
import com.example.conveting.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public void save(MemberDTO memberDTO) {
        // 디버깅 메시지 추가
        logger.debug("Saving MemberDTO: id_user={}, name_user={}", memberDTO.getIdUser(), memberDTO.getName_user());

        // id_user 값이 설정되었는지 확인
        if (memberDTO.getIdUser() == null || memberDTO.getIdUser().isEmpty()) {
            throw new IllegalArgumentException("ID must be provided");
        }

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberId = memberRepository.findByIdUser(memberDTO.getIdUser());
        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                return MemberDTO.toMemberDTO(memberEntity);
            }
        }
        return null;
    }
}
