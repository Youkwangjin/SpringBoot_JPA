package com.minipro.springweb.service.member.impl;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.entity.member.MemberEntity;
import com.minipro.springweb.repository.member.MemberRepository;
import com.minipro.springweb.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private final MemberRepository memberRepository;
    @Override
    public MemberDTO memberLogin(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 정보를 DB에서 조회를 한다.
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단한다.
         */
        Optional<MemberEntity> byUserEmail = memberRepository.findByUserEmail(memberDTO.getUserEmail());
        if (byUserEmail.isPresent()) {
            /*
                1. DB 조회 결과가 있다. (해당 이메일을 가진 회원정보가 있다.)
                2. 결과가 있으면 비밀번호 조회
             */
            MemberEntity memberEntity = byUserEmail.get();
            if (memberEntity.getUserPwd().equals(memberDTO.getUserPwd())) {
                // 비밀번호 일치하면 Controller 에게 보내주기 위해 Entity => DTO 변환 작업
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                System.out.println(dto);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // DB 조회 결과가 없다 (해당 이메일을 가진 회원정보가 없다.)
            return null;
        }
    }
}
