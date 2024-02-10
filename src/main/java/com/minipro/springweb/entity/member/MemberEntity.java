package com.minipro.springweb.entity.member;

import com.minipro.springweb.dto.member.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user") // DB에 MemberEntity 클래스에 정의한 대로 테이블 생성이 되는데 Table 명을 지정해준다.
public class MemberEntity { // 일종의 테이블 역할을 한다.

    @Id // PK 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Increment 역할
    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @Column(name = "user_email", unique = true) // unique 제약 조건
    @NonNull
    private String userEmail;

    @Column(name = "user_pwd")
    @NonNull
    private String userPwd;

    @Column(name = "user_name")
    @NonNull
    private String userName;

    @Column(name = "user_tel")
    @NonNull
    private String userTel;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        /*
            1. DTO 객체로 담긴 데이터를 Entity 객체로 변환해서 넘기는 작업
            2. 값을 가져와야 하니 getter 그 값을 넘겨야 하니 setter
         */
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPwd(memberDTO.getUserPwd());
        memberEntity.setUserName(memberDTO.getUserName());
        memberEntity.setUserTel(memberDTO.getUserTel());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPwd(memberDTO.getUserPwd());
        memberEntity.setUserName(memberDTO.getUserName());
        memberEntity.setUserTel(memberDTO.getUserTel());
        return memberEntity;
    }
}
