package com.springboot.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// 부모클래스를 매핑한다
@MappedSuperclass
//엔티티 변경을 (상태변화를) 실시간으로 감지 (감시자)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { //추상화된 메서드

    //생성 감지 후 자동으로 변경
    @CreatedDate
    private LocalDateTime createdAt;

    //수정을 감지해서 값을 자동으로 변경
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
