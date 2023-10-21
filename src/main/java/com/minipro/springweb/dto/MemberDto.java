package com.minipro.springweb.dto;

import com.minipro.springweb.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 자동으로 만들어준다.
@AllArgsConstructor
@ToString
// Dto 라는 클래스는 회원정보에 필요한 내용들을 필드로 정리하고 그 필드에 대해서 private으로 지정
public class MemberDto {

    private String userid;
    private String userpwd;
    private String username;
    private String usertel;
    private String useremail;
    private String useraddr;
    private String userjumin;
    private String userheight;
    private String userweight;
    private String usershoessize;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setUserid(memberEntity.getUserid());
        memberDto.setUserpwd(memberEntity.getUserpwd());
        memberDto.setUsername(memberEntity.getUsername());
        memberDto.setUsertel(memberEntity.getUsertel());
        memberDto.setUseremail(memberEntity.getUseremail());
        memberDto.setUseraddr(memberEntity.getUseraddr());
        memberDto.setUserjumin(memberEntity.getUserjumin());
        memberDto.setUserheight(memberEntity.getUserheight());
        memberDto.setUserweight(memberEntity.getUserweight());
        memberDto.setUsershoessize(memberEntity.getUsershoessize());

        return memberDto;
    }
}
