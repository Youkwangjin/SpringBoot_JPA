package com.minipro.springweb.service.member.impl;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.entity.member.MemberEntity;
import com.minipro.springweb.repository.member.MemberRepository;
import com.minipro.springweb.service.member.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberListServiceImpl implements MemberListService {

    private final MemberRepository memberRepository;
    @Override
    public List<MemberDTO> memberListAll() {
        /*
            1. Entity 여러 개가 담긴 List 객체를 DTO가 여러 개 담긴 List 객체로 변환 작업
         */
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    @Override
    public MemberDTO findByUserId(Long userId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(userId);
        /*
            1. Optional 객체 내부에 값이 존재하는지 여부를 확인한다.
            2. 값이 존재한다면 get() 메소드를 사용하여 Optional 객체 내부의 MemberEntity 객체에 접근한다.
            3. DTO 형태로 변환하여 반환
         */
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
}
