package com.minipro.springweb.service.member.impl;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.entity.member.MemberEntity;
import com.minipro.springweb.repository.member.MemberRepository;
import com.minipro.springweb.service.member.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberUpdateServiceImpl implements MemberUpdateService {

    private final MemberRepository memberRepository;
    @Override
    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    @Override
    public void memberUpdateData(MemberDTO memberDTO) {
        // save 메서드는 memberRepository id가 있으면 update 쿼리문을 날려준다. 없으면 insert 쿼리문!
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }
}
