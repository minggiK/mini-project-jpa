package com.springboot.like.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.board.entity.Board;
import com.springboot.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;  //레퍼타입의 Long 을 쓰는게 일반적 : 초기갑 null (영속성 전이때 확인에 용이)

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "board_id")
    private Board board;



}
