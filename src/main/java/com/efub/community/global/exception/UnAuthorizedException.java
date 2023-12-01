package com.efub.community.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message) {
        super(message);
    }

}
