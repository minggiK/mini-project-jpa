package com.springboot.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User exists"),
    BOARD_NOT_FOUND(404, "Board not found"),
    BOARD__CODE_EXISTS(409, "Board exists"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    CANNOT_CHANGE_COMMENT(403, "Comment can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");  // TO 추가된 부분

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
