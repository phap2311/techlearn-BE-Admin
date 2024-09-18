package com.techzen.techlearn.exception;

import com.techzen.techlearn.enums.ErrorCode;
import lombok.*;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}