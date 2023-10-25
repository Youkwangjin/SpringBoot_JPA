package com.minipro.springweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 시간확인이 필요할 때 상속으로 데이터 값을 넘겨받기 위해 따로 클래스 작성
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BoardBaseEntity {
    @CreationTimestamp
    @Column(updatable = false) // 수정할 시 관여하지 않는다. 
    private LocalDateTime createdtime;

    @UpdateTimestamp
    @Column(insertable = false) // 입력할 시 관여하지 않는다.
    private LocalDateTime updatedtime;
}
