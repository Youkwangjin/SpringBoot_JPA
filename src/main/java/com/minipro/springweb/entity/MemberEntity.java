package com.minipro.springweb.entity;


import com.minipro.springweb.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user") // DB에 있는 테이블 이름을 말한다.
// 데이터베이스 테이블을 자바 객체처럼 사용할 수 있는 역할을 한다.
public class MemberEntity {
    @Id // PRIMARY KEY 를 의미.
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 의미.
    private String userid;
    @Column
    private String userpwd;
    @Column
    private String username;
    @Column
    private String usertel;
    @Column
    private String useremail;
    @Column
    private String useraddr;
    @Column(unique = true) // unique 제약조건
    private String userjumin;
    @Column
    private String userheight;
    @Column
    private String userweight;
    @Column
    private String usershoessize;

    public static MemberEntity toMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserid(memberDto.getUserid());
        memberEntity.setUserpwd(memberDto.getUserpwd());
        memberEntity.setUsername(memberDto.getUsername());
        memberEntity.setUsertel(memberDto.getUsertel());
        memberEntity.setUseremail(memberDto.getUseremail());
        memberEntity.setUseraddr(memberDto.getUseraddr());
        memberEntity.setUserjumin(memberDto.getUserjumin());
        memberEntity.setUserheight(memberDto.getUserheight());
        memberEntity.setUserweight(memberDto.getUserweight());
        memberEntity.setUsershoessize(memberDto.getUsershoessize());

        return memberEntity;
    }
}
