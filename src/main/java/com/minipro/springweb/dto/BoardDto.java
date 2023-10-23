package com.minipro.springweb.dto;


import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 자동으로 만들어준다.
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
@ToString
// DTO (Data Transfer Object), VO, Bean : 데이터를 전송할 때 사용하는 객체
public class BoardDto {
    private Long id;
    private String boardwriter;
    private String boardpass;
    private String boardtitle;
    private String boardcontents;
    private int boardhit;
    private LocalDateTime boardcreatedtime;
    private LocalDateTime boardupdatedtime;
}