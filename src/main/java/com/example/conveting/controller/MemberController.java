package com.example.conveting.controller;

import com.example.conveting.dto.MemberDTO;
import com.example.conveting.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @GetMapping("/save")
    public String saveForm() {
        return "save2";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        // 디버깅 메시지 추가
        logger.debug("Received MemberDTO: id_user={}, name_user={}", memberDTO.getIdUser(), memberDTO.getName_user());

        // id_user 값이 설정되었는지 확인
        if (memberDTO.getIdUser() == null || memberDTO.getIdUser().isEmpty()) {
            throw new IllegalArgumentException("ID must be provided");
        }

        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            session.setAttribute("name", loginResult.getName_user());
            return "main";
        } else {
            return "login";
        }
    }
}
