package com.minipro.springweb.dto.member;


import com.minipro.springweb.entity.member.MemberEntity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor    // 기본 생성자를 자동으로 만들어준다.
@AllArgsConstructor   // DTO 에서 설정한 필드이 메개변수로 하는 생성자를 자동으로 만들어준다.
@ToString             // DTO 객체에서 가지고 있는 필드값을 출력할 때 사용하는 toString 메서드를 자동으로 만들어준다.
public class MemberDTO {
    /*
        1. DTO 라는 클래스는 회원 정보의 필요한 내용들을 필드로 정의
        2. 필드로 작성하는 규칙은 모두 private 정의한다.
        3. 필드를 사용하기 위해선 직접적으로 접근을 제한하고 Getter, Setter 형태로 접근하게 한다.
     */
    private int userId;
    private String userPwd;
    private String userName;
    private String userTel;
}
