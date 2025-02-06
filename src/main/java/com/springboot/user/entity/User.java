package com.springboot.user.entity;


import com.springboot.audit.BaseEntity;
import com.springboot.board.entity.Board;
import com.springboot.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String nickname;

    //생성할 때의 상태를 넣어줘야한다. -? enum도 레퍼런스타입이라 초기화 안되면 null로 들어가서 NPE 터짐 
    //상수를 입력하면 등록 순서가 생기는데, ORDINAL 로 지정하면 중간에 상수를 추가할 때, 번호가 바뀔 수 있어서 STRING 으로 지정
    @Enumerated(value = EnumType.STRING)
    //조건을 추가하기 위해 작성
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Board> boards = new ArrayList<>();

    //List<> list = new ArrayList<>(); 로 초기화 하는 이유?  레퍼런스타입 기본 값 null -> List에 값 추가하려면 add -> null에 add하면 NPE
    //String 은 참조자료형인데 value를 빈 배열로 가지고 있어서 기본값이 "" 빈문자열이다 // 많이 쓰니까 초기값은 ""로 정의
    //JPA 에서는 양방향 매핑은 적게 설정하는게 좋다.

    
    //enum : 상수들의 집합, 클래스의 일종 
    public enum UserStatus {
        //public static final 이 생략 되어있다.
        USER_ACTIVE,
        USER_SLEEP,
        USER_QUIT;
        //DB 에는 상수만 들어가고 상수가 갖고있는 param 등이 안들어간다. 
    }

}
