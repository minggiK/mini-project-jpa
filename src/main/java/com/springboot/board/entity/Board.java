package com.springboot.board.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.comment.entity.Comment;
import com.springboot.like.entity.Like;
import com.springboot.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//JPA 에서 객체를 생성할때 기본생성자가 필요하다. (엔티티를 DB 와 매핑해서 객체를 DB 에 저장하거나, 불러올 때 사용해야한다)
//JPA 가 엔티티 객체를 생성할때 Reflection 을 사용하기 때문 -> DB에서 데이터를 가져아서 객체로 변환할 때 JPA는 기본생성자를 이용해 엔티티 객체를 먼저 만들고 후에 값 세팅
 //기본생성자가 없으면 JPA가 해당 클래스를 객체로 만들 수 없어서 엔티티를 정상적으로 처리하지 못한다. -> ResponseDto 로 매핑할때
//private 으로 필드를 설정하면 기본생성자를 만들어야한다.??
//(lombok 사용하면 기본생성자가 누락될수있어서 명시적으로 써야한다)
//필드로 final 을 설정하면 초기값을 가지고 있어야해서 ->   @NoArgsConstructor (force = true)
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id") //테이블 기준으로 작성
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Like> likes = new ArrayList<>();
}
