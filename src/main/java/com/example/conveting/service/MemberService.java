package com.example.conveting.service;

import com.example.conveting.dto.MemberDTO;
import com.example.conveting.entity.MemberEntity;
import com.example.conveting.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public void save(MemberDTO memberDTO) {
        // 디버깅 메시지 추가
        logger.debug("Saving MemberDTO: id_user={}, name_user={}", memberDTO.getIdUser(), memberDTO.getName_user());

        // id_user 값이 설정되었는지 확인
        if (memberDTO.getIdUser() == null || memberDTO.getIdUser().isEmpty()) {
            throw new IllegalArgumentException("ID must be provided");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        logger.debug("Attempting login for id_user={}", memberDTO.getIdUser());

        Optional<MemberEntity> byMemberId = memberRepository.findByIdUser(memberDTO.getIdUser());
        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            // 암호화된 비밀번호 비교
            if (passwordEncoder.matches(memberDTO.getPassword(), memberEntity.getPassword())) {
                logger.info("Login successful for id_user={}", memberDTO.getIdUser());
                return MemberDTO.toMemberDTO(memberEntity);
            } else {
                logger.warn("Login failed for id_user={}: Incorrect password", memberDTO.getIdUser());
            }
        } else {
            logger.warn("Login failed: No user found with id_user={}", memberDTO.getIdUser());
        }
        return null;
    }

    public MemberEntity findByIdUser(String idUser) {
        Optional<MemberEntity> memberEntity = memberRepository.findByIdUser(idUser);
        return memberEntity.orElse(null); // 존재하지 않으면 null 반환
    }
}
