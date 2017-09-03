package com.chong.exception;

import com.chong.enums.ResultCode;
import lombok.Getter;

/**
 * 参数异常 包括参数为null异常，重复的参数，数据库查询不出结果等
 */
public class ParamException extends RuntimeException {

    @Getter
    private final ResultCode code;

    private ParamException(ResultCode code) {
        super(code.getComment());
        this.code = code;
    }

    private ParamException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public static class NullParamException extends ParamException {
        public NullParamException() {
            super(ResultCode.NULL);
        }
        public NullParamException(String message) {
            super(ResultCode.NULL, message);
        }
    }

    public static class NotInDBException extends ParamException {
        public NotInDBException() {
            super(ResultCode.NOT_IN_DB);
        }

        public NotInDBException(Long id) {
            super(ResultCode.NOT_IN_DB, String.format("id{%d}在数据库中查询不到结果", id));
        }
        public NotInDBException(String message) {
            super(ResultCode.NOT_IN_DB, message);
        }
    }

    public static class MalformedParamException extends ParamException {
        public MalformedParamException() {
            super(ResultCode.NOT_MATCH);
        }

        public MalformedParamException(String message) {
            super(ResultCode.NOT_MATCH, message);
        }
    }
}