package com.minipro.springweb.service;


import com.minipro.springweb.dto.MemberDto;
import com.minipro.springweb.entity.MemberEntity;
import com.minipro.springweb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Spring이 관리해주는 객체(Spring bean)로 등록
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDto memberDto) {
        // 1. Dto -> Entity 객체로 변환
        // 2. Repository save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
        // Repository save 메서드 호출 (조건 : Entity 객체를 넘겨줘야 한다.
    }

    public MemberDto login(MemberDto memberDto) {
        /*
            1. 회원이 입력한 아이디로 DB에서 조회한다.
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단한다.
         */
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(memberDto.getUserid());
        if(byUserid.isPresent()) {
            // 조회 결과가 있다(해당 아이디를 가진 회원 정보가 있다)
            MemberEntity memberEntity = byUserid.get(); // Optional로 감싸진 객체를 get()를 통해 가져온다.
            if(memberEntity.getUserpwd().equals(memberDto.getUserpwd())) {
                // 비밀번호가 일치하는 경우
                // Entity -> Dto 객체로 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            } else {
                // 비밀번호가 일치하지 않는 경우
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디를 가진 회웑 정보가 없다)
            return null;
        }
    }

    public List<MemberDto> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));

/*          MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
            memberDtoList.add(memberDto);*/
        }
        return memberDtoList;
    }
}
