package com.springboot.user.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.user.entity.User;
import com.springboot.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //회운가입 할때
    public User createUser(User user) {
        //1.검증을 해야한다 (닉네임이 중복인지)
        verifyExistsNickname(user.getNickname());
        //DB에 저장해야한다
        return userRepository.save(user);
    }

    public User updateUser(User user) {
       //1. 검증로직


        return null;
    }

    public User getUser(long userId) {
        return null;
    }

    //page로 반환 해야함
    public Page<User> getUsers(int page, int size) {
        return null;
    }

    public void deleteUser(long userId) {

        //delete 날리면 상태변경 ACTIVE -> SLEEP
        //회원이 존재하는지 확인
        User findUser = findVerifiedUser(userId);
        //존재하면 user의 상태 변경
        findUser.setUserStatus(User.UserStatus.USER_QUIT);
        //바꼈으면 저장
        //DB에 저장할 때는 원래의 값으로 찾아서 바꿀수 있는건 바꾼다음에 저장을 해야한다. PK가 비어있을때는 POST만 가능
        // 무조건 저장해야한다.
        userRepository.save(findUser);

//        //존재하면 User의 상태를 변경 -> JS 에서 쿼리문을 던져서
//        if(findUser.getUserStatus() == User.UserStatus.USER_ACTIVE
//                || findUser.getUserStatus() == User.UserStatus.USER_SLEEP) {
//
//        }
    }

    //검증 로직
    //1. 등록된 유저가 있는지 nickname 으로 구분
    public void verifyExistsNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);
//        User findUser = user.orElseThrow(()-> new BusinessLogicException(ExceptionCode.USER_EXISTS));

        if(user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    //2.식별자로 조회 (URI 에서 pathable, param 으로 userId로 받기 때문에)
    public User findVerifiedUser (long userId) {
        //repository에서 찾고(없으면 예외처리) 반환
        //JpaRepository 가 Optional
        Optional<User> user = userRepository.findById(userId);

        //Optional 을 벗겨서 갖고 있는 객체타입을 반환한다.
        //메서드 마다 반환타입을 변경할 수 있다.
        return user.orElseThrow(()-> new BusinessLogicException(ExceptionCode.USER_EXISTS));

    }

}
