package com.minipro.springweb.service.member.impl;

import com.minipro.springweb.repository.member.MemberRepository;
import com.minipro.springweb.service.member.MemberDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeleteServiceImpl implements MemberDeleteService {

    private final MemberRepository memberRepository;
    @Override
    public void deleteById(Long userId) {
        memberRepository.deleteById(userId);
    }
}
