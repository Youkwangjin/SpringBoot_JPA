package com.minipro.springweb.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user") // DB에 MemberEntity 클래스에 정의한 대로 테이블 생성이 되는데 Table 명을 지정해준다.
public class MemberEntity { // 일종의 테이블 역할을 한다.

    @Id // PK 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Increment 역할
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_email", unique = true) // unique 제약 조건
    private String userEmail;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_tel")
    private String userTel;

}
