package com.minipro.springweb.entity.board;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BoardBaseEntity { // 시간 정보를 다룰 수 있도록 하는 클래스

    @CreationTimestamp
    @Column(name = "board_createTime", updatable = false) // 수정 시 관여하지 않는다.
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "board_updateTime", insertable = false) // 입력시에 관여하지 않는다.
    private LocalDateTime updateTime;
}
