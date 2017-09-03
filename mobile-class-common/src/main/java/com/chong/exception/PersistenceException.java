package com.chong.exception;

import com.chong.enums.ResultCode;
import lombok.Getter;

/**
 * 持久层异常类
 * Created by LXChild on 05/04/2017.
 */
public class PersistenceException extends RuntimeException {

    @Getter
    private final ResultCode code;

    private PersistenceException(ResultCode code) {
        super(code.getComment());
        this.code = code;
    }

    private PersistenceException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public static class RepetitiveParamException extends PersistenceException {
        public RepetitiveParamException() {
            super(ResultCode.REPETITIVE);
        }
        public RepetitiveParamException(String message) {
            super(ResultCode.REPETITIVE, message);
        }
    }
}
