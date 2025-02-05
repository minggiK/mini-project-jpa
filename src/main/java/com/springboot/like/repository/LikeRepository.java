package com.springboot.like.repository;

import com.springboot.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//제네릭타입은 레퍼런스타입만 받을 수 있다.
//
public interface LikeRepository extends JpaRepository<Like, Long> {
//    Optional<>  : NPE 방지(있거나 없거나 : 없을때 empty() 반환)
    //Service 층에서 기능에서 사용할 때 Optional<Entity> -> 반환 Entity 왜??
    //orElseThrow() : false 면 예외를 던지고, true 면 Optional 을 벗겨내고 Entity 로 반환한다
}
